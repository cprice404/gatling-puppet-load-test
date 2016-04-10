#!/usr/bin/env bash

sut_host="$1"

ssh root@${sut_host} "mkdir -p ~/.ssh"
echo 'ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCnfjgtX/6HKgM+3eKd3FGC6dvVrxWT6qGqC2U0wnEQnIfIAocT9pKN7vxU8xjcpmr/KE9wW5IdlupTZ70D/R7vGwGjyd8hB+8nxreRmzN7roW5Xzy1mbBfZFCji7qF7WCZoW4w+bN7bFHMkKaw+0SeyORQFAlYoQbVcQyLvje2Ie4TFdFfmYs6V0lNCqxUPukGvh20dvB4PTDA2MLDGjXpYuFCaYeHe0oX50gefSjG4ZF9+p8D03lacHYaDycyyY+WaSXYrygb1BkJ7C7wSPffCJ9Lmra1iUC1ZMJ0gN3e7fjsUoIm4Vvnhk8TUfOPFmsnQiWpRvXkobrAGLt4GEfZ wayne@pathways' |ssh root@${sut_host} 'cat >> .ssh/authorized_keys'
