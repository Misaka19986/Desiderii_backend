"""
URL configuration for Desiderii_backend_p project.

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/5.0/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path, include
from desiderii.views import UserView, ArticleView
from rest_framework_simplejwt.views import TokenObtainPairView, TokenRefreshView, TokenVerifyView
from django.conf.urls.static import static
from django.conf import settings
from django.views.generic.base import TemplateView

urlpatterns = [
    path('', TemplateView.as_view(template_name='index.html')),
    path('admin/', admin.site.urls),
    path('token/', include([
        path('obtain', TokenObtainPairView.as_view()),
        path('refresh', TokenRefreshView.as_view()),
        path('verify', TokenVerifyView.as_view())
    ])),
    path('user/', include([
        path('login', UserView.userLogin),
        path('register', UserView.userRegister),
        path('logout', UserView.UserLogout.as_view()),
        path('getUserInfoBySession', UserView.GetUserInfoBySession.as_view()),
        path('updateUserInfoBySession', UserView.UpdateUserInfoBySession.as_view()),
        path('uploadUserAvatar', UserView.UploadUserAvatar.as_view()),
    ])),
    path('article/', include([
        path('getAllArticlePreviews', ArticleView.getAllArticlePreviews),
        path('preview/<str:title>', ArticleView.GetArticleByTitle.as_view()),
        path('getUserArticlePreviews', ArticleView.GetUserArticlePreviews.as_view()),

    ])),
]

urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)
