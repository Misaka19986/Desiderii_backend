from desiderii.models import User
from rest_framework import serializers

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ('id', 'name', 'phone', 'email', 'password', 'create_time', 'avatar', 'signature')

class UserDeserializer(serializers.Serializer):
    name = serializers.CharField(max_length=255, label='用户名', required=True)
    password = serializers.CharField(max_length=255, label='密码', required=False)
    phone = serializers.CharField(max_length=11, label='电话号码', required=False)
    email = serializers.CharField(max_length=32, label='邮箱', required=False)

    def create(self, validated_data):
        # Custom logic to create a User instance
        return User.objects.create(**validated_data)

    def update(self, instance, validated_data):
        # Custom logic to update a User instance
        instance.name = validated_data.get('name', instance.name)
        instance.password = validated_data.get('password', instance.password)
        instance.phone = validated_data.get('phone', instance.phone)
        instance.email = validated_data.get('email', instance.email)
        instance.save()
        return instance