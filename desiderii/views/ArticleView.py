import logging
import re

import json
from django.http import JsonResponse

from desiderii.models import Article, User
from desiderii.serializers.ArticleSerializer import ArticleSerializer

from rest_framework.views import APIView


# Logger
logger = logging.getLogger('ArticleView')
def getAllArticlePreviews(request):
    articles = Article.objects.all()

    previews = {}

    index = 0

    for article in articles:
        # 如果文章已经删除，则不显示
        if article.is_delete == 1:
            continue
        # 获取作者
        author = article.uid.name
        # 内容的前50个字
        short_content = article.content[:100]
        # preview字典
        preview = {'title': article.title, 'content': short_content,
                   'create_time': article.create_time, 'author': author}

        previews[index] = preview

        index += 1

    return JsonResponse({'code': '100', 'message': 'Get all previews success',
                         'data': previews, 'count': len(previews)})

class GetArticleByName(APIView):
    def post(self, request):
        user = request.user
class UploadArticle(APIView):
    def post(self, request, title):
        user = request.user

        article = Article.objects.get(title=title)

        if article is None:
            logger.info('No such article')
            return JsonResponse({'code': 101, 'message': 'No such article'})

        serializer = ArticleSerializer(article)

        return JsonResponse({'code': 100, 'message': 'Get article success', 'data': serializer.data})
