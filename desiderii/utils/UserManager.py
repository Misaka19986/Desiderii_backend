from django.contrib.auth.models import BaseUserManager
from django.contrib.auth.hashers import make_password

class UserManager(BaseUserManager):
    def create_user(self, name, password=None, phone=None, email=None):
        if not name:
            raise ValueError('Users must have an username')

        user = self.model(
            name=name,
        )
        # 创建用户时进行加密
        password = make_password(password)
        user.password = password
        if isinstance(phone, str) and len(phone) > 0:
            user.phone = phone
        if isinstance(phone, str) and len(email) > 0:
            user.email = email

        user.save(using=self._db)
        return user

    def create_superuser(self, name, password=None, phone=None, email=None):
        # 先创建一个普通用户
        user = self.create_user(
            name=name,
            password=password,
            phone=phone,
            email=email
        )
        # 设为超级用户
        user.is_superuser = True

        user.save(using=self._db)