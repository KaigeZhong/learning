/*
 * file showstr.s
*/ 
.code16

.globl  init, begtext, begdata, begbss, endtext, enddata, endbss
.text
begtext:
.data
begdata:
.bss
begbss:
.text


init:
	movw $0,%dx
	movw $24,%cx
	movw $0x0700,%si
	movw $msg,%bp
	call showstr
end:jmp end

showstr:
	push %ds
	movw $0xb800,%ax
	movw %ax,%ds
show:
	movb $13,%ah
	movb (%bp),%al
	cmp %ah,%al
	je 1f
	movb $10,%ah
	movb (%bp),%al
	cmp %ah,%al
	je 1f
	jmp 2f
1:	movw %dx,%ax
	movw $160,%bx
	div %bl
	sub %ah,%bl
	add %bx,%dx
	sub $2,%dx
	jmp 3f
2:	movw %si,%ax
	movb (%bp),%al
	movw %dx,%bx
	movw %ax,(%bx)		#set ds:bx=ax (0xb800:bx=ax)
3:	dec %cx
	inc %bp
	add $2,%dx
	movw $0,%ax
	cmp %cx,%ax
	jne show
	pop %ds
	ret

msg:
	.byte 13,10
	.ascii "Loading system ..."
	.byte 13,10,13,10

.org 510  #指定一个地址，后面的程序或数据从这个地址值开始分配。
.word 0xAA55

.text
endtext:
.data
enddata:
.bss
endbss:
