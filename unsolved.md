# 待办事项
- 发布文章页面回车后自动提交表单（禁止回车，需要手动点击按钮确认！）
- 文章管理页面（查询该用户的所有文章，更新文章信息，删除文章）
- PersonalCenterController -> @GetMapping("/{id}"): 访问无效的用户id跳转到404页面
- AccountManagementController -> @PostMapping("avatar/update"): 上传的头像若用相同的文件名保存，上传新的头像后会导致浏览器取用缓存里的同名旧头像
