.code16
.global init # makes our label "init" available to the outside
 
init: # this is the beginning of our binary later.
  jmp init # jump to "init"

.fill 510-(.-init), 1, 0 # add zeroes to make it 510 bytes long
.word 0xaa55 # magic bytes that tell BIOS that this is bootable magic number是0x55aa，为什么我们在这里交换它们？这是因为x86是小端的，所以字节在内存中交换。
