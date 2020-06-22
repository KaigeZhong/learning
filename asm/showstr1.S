/*
 * file showstr.s
*/ 
.code16
.global _start
_start:
  mov $0xb800, %ax
  movw %ax, %es
  movb $0x41, %es:0
  movb $0x1f, %es:1 #颜色
  movb $0x41, %es:2
  movb $0x1f, %es:3 #颜色
  movb $0x41, %es:4
  movb $0x1f, %es:5 #颜色
  movb $0x41, %es:6
  movb $0x1f, %es:7 #颜色
loop:jmp loop

.org 510  #指定一个地址，后面的程序或数据从这个地址值开始分配。
.word 0xAA55
