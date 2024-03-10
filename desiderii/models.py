from django.db import models
from django.contrib.auth.models import AbstractBaseUser, PermissionsMixin
from desiderii.utils import UserManager

def avatar_name(instance, filename):
    ext = filename.split('.')[-1]
    new_name = f'{instance.name}_avatar.{ext}'
    return f'avatars/{new_name}'

class User(AbstractBaseUser, PermissionsMixin):
    id = models.BigAutoField(primary_key=True, verbose_name='用户id')
    name = models.CharField(max_length=255, unique=True, verbose_name='用户名称')
    phone = models.CharField(max_length=255, unique=True, blank=True, null=True, verbose_name='用户手机号码')
    email = models.EmailField(max_length=255, unique=True, blank=True, null=True, verbose_name='用户邮箱')
    password = models.CharField(max_length=2048, verbose_name='用户密码(hash)')
    create_time = models.DateTimeField(auto_now_add=True, verbose_name='创建时间')
    avatar = models.ImageField(upload_to=avatar_name, blank=True, null=True, verbose_name='头像')
    signature = models.CharField(max_length=255, blank=True, null=True, verbose_name='个性签名')

    is_active = models.BooleanField(default=True, blank=True, null=True, verbose_name='是否有效')
    is_superuser = models.BooleanField(default=False, verbose_name='是否为管理员')

    USERNAME_FIELD = 'name'

    objects = UserManager.UserManager()

    def __str__(self):
        return self.name

    class Meta:
        db_table = 'user'
        verbose_name = '用户'
        verbose_name_plural = '用户'

        # permissions = [
        #     ('', '')
        # ]

class Topic(models.Model):
    tid = models.BigAutoField(primary_key=True, verbose_name='板块id')
    content = models.CharField(max_length=255, verbose_name='内容')
    create_time = models.DateTimeField(auto_now_add=True, verbose_name='创建时间')

    def __str__(self):
        return self.content

    class Meta:
        db_table = 'topic'
        verbose_name = '话题'
        verbose_name_plural = '话题'
class Article(models.Model):
    aid = models.BigAutoField(primary_key=True, verbose_name='动态id')
    # 用户注销后文章仍保留，但外键为null
    uid = models.ForeignKey(User, on_delete=models.SET_NULL, null=True, verbose_name='所有者id')
    # 删除板块后删除全部文章
    tid = models.ForeignKey(Topic, on_delete=models.CASCADE, null=True, verbose_name='所属板块id')
    title = models.CharField(max_length=255, verbose_name='标题')
    content = models.TextField(verbose_name='内容')
    create_time = models.DateTimeField(auto_now_add=True, verbose_name='创建时间')
    update_time = models.DateTimeField(auto_now=True, verbose_name='更新时间')
    is_delete = models.BooleanField(default=False, verbose_name='逻辑删除 0-未删除 1-已删除')

    class Meta:
        db_table = 'article'

class Comment(models.Model):
    cid = models.BigAutoField(primary_key=True, verbose_name='评论id')
    uid = models.ForeignKey(User, on_delete=models.SET_NULL, null=True, verbose_name='所有者id')
    ccid = models.ForeignKey('self', on_delete=models.SET_NULL, null=True, verbose_name='父评论id')
    aid = models.ForeignKey(Article, on_delete=models.CASCADE, verbose_name='所属动态id')
    content = models.TextField(verbose_name='内容')
    create_time = models.DateTimeField(auto_now_add=True, verbose_name='创建时间')

    def __str__(self):
        return f"Comment {self.cid}"

    class Meta:
        db_table = 'comment'
        verbose_name = '评论'
        verbose_name_plural = '评论'

class Tag(models.Model):
    tid = models.BigAutoField(primary_key=True, verbose_name='标签id')
    content = models.CharField(max_length=255, verbose_name='内容')
    create_time = models.DateTimeField(auto_now_add=True, verbose_name='创建时间')

    def __str__(self):
        return self.content

    class Meta:
        db_table = 'tag'
        verbose_name = '标签'
        verbose_name_plural = '标签'

class ArticleTag(models.Model):
    aid = models.ForeignKey(Article, on_delete=models.CASCADE, verbose_name='动态id')
    tid = models.ForeignKey(Tag, on_delete=models.CASCADE, verbose_name='tag id')

    class Meta:
        db_table = 'article_tag'
        verbose_name = '动态标签关联'
        verbose_name_plural = '动态标签关联'