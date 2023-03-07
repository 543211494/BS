# 1.year

报名年份，若year=-1表示未开放报名

# 2.token-{userId}-{uuid}

用户登录token,userId表示用户id,value值为用户对象的json字符串

如：token-1-faafb37f-ee46-4eeb-8244-8a9517f17eb9

# 3.major

全部专业列表，value值为一个Major列表的json字符串

# 4.major-{year}

某一年招生专业列表，value值为一个Major列表的json字符串

year表示年份，如major-2022

# 5.registration-{year}-{userId}

报名表示数据，value值为Registration对象的json字符串

year表示报名年份，userId表示用户id，如：registration-2022-1

# 6.match-lock

匹配锁，防止同一时间开始两次匹配

# 7.match-year

一轮匹配中需要处理的报名表年份

# 8.match-number

一轮匹配中尚未处理的消息数

# 9.match-number-lock

读写match-number时上的锁

# 10.comment-{page}-{number}

搜索留言的缓存key,其value值为Comment对象数组的JSON字符串
其中page为页码，number为一页的留言数

# 11.code-{identity}

用于重置密码的邮箱验证码,identity为用户帐号
如：code-360203200203081256