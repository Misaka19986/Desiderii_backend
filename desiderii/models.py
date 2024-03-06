from django.db import models
from django.contrib.auth.models import AbstractBaseUser, PermissionsMixin
from desiderii.utils import UserManager

class User(AbstractBaseUser, PermissionsMixin):
    id = models.BigAutoField(primary_key=True, verbose_name='用户id')
    name = models.CharField(max_length=255, unique=True, verbose_name='用户名称')
    phone = models.CharField(max_length=255, unique=True, blank=True, null=True, verbose_name='用户手机号码')
    email = models.EmailField(max_length=255, unique=True, blank=True, null=True, verbose_name='用户邮箱')
    password = models.CharField(max_length=2048, verbose_name='用户密码(hash)')
    create_time = models.DateTimeField(auto_now_add=True, verbose_name='创建时间')
    avatar = models.ImageField(upload_to='avatars', blank=True, null=True, verbose_name='头像')
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