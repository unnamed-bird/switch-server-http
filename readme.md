# 本地开发说明

在SwitcherApplication 主函数的启动命令新增一个参数 -Dconfig=resource文件下的server.config 绝对路径


# 打包

maven  -Dmaven.test.skip=true -P PRO

复制 swServer.sh , resource目录下server.config 和jar 放入同一目录 执行swServer.sh 即可
