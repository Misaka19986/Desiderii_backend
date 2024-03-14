from desiderii.models import Article
from rest_framework import serializers

class ArticleSerializer(serializers.ModelSerializer):
    author = serializers.SerializerMethodField()
    topic = serializers.SerializerMethodField()

    class Meta:
        model = Article
        fields = ('aid', 'title', 'content',
                  'create_time', 'update_time', 'author', 'topic')

    def get_author(self, obj):
        author = obj.uid
        if author is None:
            return ''

        return author.name

    def get_topic(self, obj):
        topic = obj.tid
        if topic is None:
            return ''

        return topic.content