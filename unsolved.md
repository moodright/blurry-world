# 待办事项
- PersonalCenterController -> @GetMapping("/{id}"): 访问无效的用户id跳转到404页面
- AccountManagementController -> @PostMapping("avatar/update"): 上传的头像若用相同的文件名保存，上传新的头像后会导致浏览器取用缓存里的同名旧头像
