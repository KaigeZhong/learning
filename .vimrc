" Specify a directory for plugins
" - For Neovim: stdpath('data') . '/plugged'
" - Avoid using standard Vim directory names like 'plugin'
call plug#begin('~/.vim/plugged')

" Make sure you use single quotes

Plug 'scrooloose/nerdtree'


" Initialize plugin system
call plug#end()


"  ###################NERDTreeToggle#########
map <C-n> :NERDTreeToggle<CR>

"  ####################ctags#################
" 
" ;符号不可省略，表示若当前目录中不存在tags， 则在父目录中寻找。
set tags=tags;  
"-R: 循环生成子目录的 tags
" 以下参数是为了对c++支持
"–c++-kinds=+px : 记录 c++ 文件中的函数声明和各种外部和前向声明
"–fields=+iaS : ctags 要求描述的信息，其中i表示如果有继承，则标识出父类；a 表示如果元素是类成员的话，要标明其调用权限（即是 public 还是 private）；S 表示如果是函数，则标识函数的 signature。
"–extra=+q: 强制要求ctags做如下操作—如果某个语法元素是类的一个成员，ctags默认会给其记录一行，可以要求ctags对同一个语法元素再记一行，这样可以保证在VIM中多个同名函数可以通过路径不同来区分。
map <C-F12> :!ctags -R --c++-kinds=+p --fields=+iaS --extra=+q .<CR> 

"  ####################cscope################
"-R: 在生成索引文件时，搜索子目录树中的代码
"-b: 只生成索引文件，不进入cscope的界面
"-q: 生成cscope.in.out和cscope.po.out文件，加快cscope的索引速度
"-k: 在生成索引文件时，不搜索/usr/include目录
map <C-F11> :!cscope -Rbkq <CR>
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
