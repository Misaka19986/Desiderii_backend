from django.contrib.auth.models import BaseUserManager
from django.contrib.auth.hashers import make_password

class UserManager(BaseUserManager):
    def create_user(self, username, password=None, phone=None, email=None):
        if not username:
            raise ValueError('Users must have an username')

        user = self.model(
            name=username,
        )
        # 创建用户时进行加密
        password = make_password(password)
        user.password = password
        if len(phone) > 0:
            user.phone = phone
        if len(email) > 0:
            user.email = email

        user.save(using=self._db)
        return user

    def create_superuser(self, username, password=None, phone=None, email=None):
        # 先创建一个普通用户
        user = self.create_user(
            username,
            password=password,
            phone=phone,
            email=email
        )
        # 设为超级用户
        user.is_superuser = True

        user.save(using=self._db)