# 在使用git过程中，实际遇到的问题
## 配置环境
1. 要想都有提交的权限，需要在服务器端，添加公钥，注意，不要添加换行符；
1. 通过git remote add origin git@git.oschina.net:genggeng/Research.git，这样可以关联本地的仓库和远程服务器的仓库，当前的master分支和远程服务器的master分支相对于；
1. git push -u origin master 之后的提交就可以省去后面的参数了，直接git push就可以了。
## 实际的版本控制过程中
1. 通过git push origin master推动本地的分支数据，git push origin dev会在远程服务器端新建dev分支，并将本地的数据推送到该分支。
1. git reset --hard HEAD^  （表示head指向回退到上一个版本）
1. git reset --hard 3628164
1. 当本地的版本低于中心仓库的版本，这时候需要pull中心仓库的代码再提交时，git pull --rebase，--rebase选项告诉Git把本地待提交移到同步了中央仓库修改后的master分支的顶部，不然会有个多余的提交。
如果你碰到了冲突，但发现搞不定，不要惊慌。只要执行下面这条命令，就可以回到你执行git pull --rebase命令前的样子：git rebase --abort
