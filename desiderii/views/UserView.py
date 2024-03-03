import logging
import re

import json
from django.http import JsonResponse

from django.contrib.auth import authenticate, login, logout
from rest_framework.decorators import api_view, authentication_classes, permission_classes
from rest_framework.permissions import IsAuthenticated
from rest_framework.views import APIView
from rest_framework_jwt.authentication import JSONWebTokenAuthentication
from rest_framework_jwt.settings import api_settings

from django.contrib.auth.hashers import make_password, check_password

from desiderii.models import User

# JWT
jwt_payload_handler = api_settings.JWT_PAYLOAD_HANDLER
jwt_encode_handler = api_settings.JWT_ENCODE_HANDLER

# Logger
logger = logging.getLogger('UserView')

def userLogin(request):
    obj = json.loads(request.body)
    username = obj.get('name', None)
    password = obj.get('password', None)

    if username is None or password is None:
        logger.info('Empty attr')
        return JsonResponse({'code': 101, 'message': 'Empty Attr'})

    # # 过rest framework的验证器
    # # 不包括密码验证
    # user = authenticate(username=username, password=password)
    # if user is None:
    #     return JsonResponse({'code': 101, 'message': 'JWTauth or Sessionauth failed'})

    # 用用户名寻找用户
    if not User.objects.filter(name=username).exists():
        # 找不到用户
        logger.info('Wrong name or password')
        return JsonResponse({'code': 101, 'message': 'Wrong name or password'})
    else:
        userDB = User.objects.get(name=username)
        # 判断是否激活
        if not userDB.is_active:
            logger.info('Not actived')
            return JsonResponse({'code': 101, 'message': 'Not actived'})
        # 匹配密码
        if check_password(password, userDB.password):
            # 密码正确
            login(request, userDB)

            logger.info('Success')
            return JsonResponse({'code': 100, 'message': 'Login success'})

    logger.info('Failed')
    return JsonResponse({'code': 101, 'message': 'Login failed'})


def userRegister(request):
    obj = json.loads(request.body)
    username = obj.get('name', None)
    password = obj.get('password', None)
    phone = obj.get('phone', None)
    email = obj.get('email', None)

    if username is None or password is None:
        logger.info('Empty attr')
        return JsonResponse({'code': 201, 'message': 'Empty Attr'})

    if not re.match(r'^(?![\d]+$)(?![a-zA-Z]+$)(?![^\da-zA-Z]+$)([^\u4e00-\u9fa5\s]){6,20}$', password):
        logger.info('Invalid password')
        return JsonResponse({'code': 201, 'message': 'Invalid password'})

    if (len(phone) > 0 and not re.match(r'^\d{11}$', phone)) \
            or (len(email) > 0 and not re.match(r'^([a-zA-Z0-9]+[-_\.]?)+@[a-zA-Z0-9]+\.[a-z]+$', email)):
        logger.info('Wrong phone or email')
        return JsonResponse({'code': 201, 'message': 'Wrong phone or email'})

    if User.objects.filter(name=username).exists():
        logger.info('Username has been registered')
        return JsonResponse({'code': 202, 'message': 'Form has been registered'})

    if len(email) > 0 and User.objects.filter(email=email).exists():
        logger.info('Email has been registered')
        return JsonResponse({'code': 203, 'message': 'Email has been registered'})

    User.objects.create_user(username=username, password=password, phone=phone, email=email)

    logger.info('Success')
    return JsonResponse({'code': 200, 'message': 'Register success'})


class UserAPI(APIView):
    def userLogout(request):
        obj = json.loads(request.body)
        username = obj.get('name', None)

        logout(request)

        return JsonResponse({'code': 100, 'message': 'Logout success'})
