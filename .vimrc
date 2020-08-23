
"######################vim-plug插件管理器配置#########################
"
" Specify a directory for plugins
" - For Neovim: stdpath('data') . '/plugged'
" - Avoid using standard Vim directory names like 'plugin'
call plug#begin('~/.vim/plugged')

" Make sure you use single quotes

Plug 'scrooloose/nerdtree', { 'tag': '6.9.0' }
Plug 'Chiel92/vim-autoformat'
Plug 'vimcn/vimcdoc', { 'tag': '1.9.0' }

" Initialize plugin system
call plug#end()
nmap <C-F10> :PlugInstall<CR>

" ##################### help doc###############
"set helplang=cn

"  ###################NERDTreeToggle#########
nmap <C-n> :NERDTreeToggle<CR>

"  ####################ctags#################
" 
" vim 默认支持ctags， 无需额外安装插件
" ;符号不可省略，表示若当前目录中不存在tags， 则在父目录中寻找。
set tags=tags;  
"-R: 循环生成子目录的 tags
" 以下参数是为了对c++支持
"–c++-kinds=+px : 记录 c++ 文件中的函数声明和各种外部和前向声明
"–fields=+iaS : ctags 要求描述的信息，其中i表示如果有继承，则标识出父类；a 表示如果元素是类成员的话，要标明其调用权限（即是 public 还是 private）；S 表示如果是函数，则标识函数的 signature。
"–extra=+q: 强制要求ctags做如下操作—如果某个语法元素是类的一个成员，ctags默认会给其记录一行，可以要求ctags对同一个语法元素再记一行，这样可以保证在VIM中多个同名函数可以通过路径不同来区分。
nmap <C-F12> :!ctags -R --c++-kinds=+p --fields=+iaS --extra=+q .<CR> 
"
"ctags查找使用
"Ctrl+ ]跳到光标所在函数或者结构体的定义处
"Ctrl+ T返回查找或跳转

"  ####################cscope################
"  vim 默认支持cscope，无需额外安装插件
"-R: 在生成索引文件时，搜索子目录树中的代码
"-b: 只生成索引文件，不进入cscope的界面
"-q: 生成cscope.in.out和cscope.po.out文件，加快cscope的索引速度
"-k: 在生成索引文件时，不搜索/usr/include目录
nmap <C-F11> :!cscope -Rbkq <CR>
" 添加索引文件
if filereadable("cscope.out")
	cs add cscope.out
endif

"  ‘cscopequickfix’指定了是否使用quickfix窗口来显示cscope的结果。这是一组用逗号分隔的值。每项都包含于|csope-find|命令（s,
"  g, d, c, t, e, f, 或者i）和旗标（+, -或者0）。
"
"         ‘+’预示着显示结果必须追加到quickfix窗口。
"
"                ‘-’隐含着清空先前的的显示结果
"set cscopequickfix=s-,c-,d-,i-,t-,e-
"
"
"cscope查找使用: 
"cs find {querytype} {name}
"其中：
"{querytype} 即相对应于实际的cscope行接口数字，同时也相对应于nvi命令：
"0或者s   —— 查找这个C符号
"1或者g  —— 查找这个定义
"2或者d  —— 查找被这个函数调用的函数（们）
"3或者c  —— 查找调用这个函数的函数（们
"4或者t   —— 查找这个字符串
"6或者e  —— 查找这个egrep匹配模式
"7或者f   —— 查找这个文件
"8或者i   —— 查找#include这个文件的文件
"
"#########################vim-autoformat###################
"vim-autoformat 只是一个框架， 还需要安装对应的格式话工具, 例如clang-format for C, C++ -> apt-get install clang-format
nmap <C-F3> :Autoformat<CR>

"############################vimgrep##############
"暂时为找到csope支持汇编文件的搜索， 暂时采取vimgrep搜索；
" 没有参数g的话,则行只查找一次关键字.反之会查找所有的关键字.
" 没有参数j的话,查找后,VIM会跳转至第一个关键字所在的文件.反之,只更新结果列表(quickfix).
" 查找的结果可以用":copen"命令查看,在列表里,将光标移动至相应的位置,按回车就打开对应的文件了
nmap <C-F5> :vimgrep /switch_to/gj **/*.S
