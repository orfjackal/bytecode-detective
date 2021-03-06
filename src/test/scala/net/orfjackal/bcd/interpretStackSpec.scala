// Copyright © 2009, Esko Luontola. All Rights Reserved.
// This software is released under the MIT License. See LICENSE.txt

package net.orfjackal.bcd

import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree._
import org.specs._
object interpretStackSpec extends Specification {
  "Operating the stack" >> {
    def exec(insn: AbstractInsnNode) = {
      val stack = List(
        KnownValue(1, classOf[Int]),
        KnownValue(2, classOf[Int]),
        KnownValue(3, classOf[Int]),
        KnownValue(4, classOf[Int]))
      val c = new MethodContext(stack, Map.empty)
      c.execute(insn)
    }
    "POP" >> {
      val c = exec(new InsnNode(Opcodes.POP))
      c.stack must_== List(
        KnownValue(2, classOf[Int]),
        KnownValue(3, classOf[Int]),
        KnownValue(4, classOf[Int]))
    }
    "POP2" >> {
      val c = exec(new InsnNode(Opcodes.POP2))
      c.stack must_== List(
        KnownValue(3, classOf[Int]),
        KnownValue(4, classOf[Int]))
    }
    "DUP" >> {
      val c = exec(new InsnNode(Opcodes.DUP))
      c.stack must_== List(
        KnownValue(1, classOf[Int]),
        KnownValue(1, classOf[Int]),
        KnownValue(2, classOf[Int]),
        KnownValue(3, classOf[Int]),
        KnownValue(4, classOf[Int]))
    }
    "DUP_X1" >> {
      val c = exec(new InsnNode(Opcodes.DUP_X1))
      c.stack must_== List(
        KnownValue(1, classOf[Int]),
        KnownValue(2, classOf[Int]),
        KnownValue(1, classOf[Int]),
        KnownValue(3, classOf[Int]),
        KnownValue(4, classOf[Int]))
    }
    "DUP_X2" >> {
      val c = exec(new InsnNode(Opcodes.DUP_X2))
      c.stack must_== List(
        KnownValue(1, classOf[Int]),
        KnownValue(2, classOf[Int]),
        KnownValue(3, classOf[Int]),
        KnownValue(1, classOf[Int]),
        KnownValue(4, classOf[Int]))
    }
    "DUP2" >> {
      val c = exec(new InsnNode(Opcodes.DUP2))
      c.stack must_== List(
        KnownValue(1, classOf[Int]),
        KnownValue(2, classOf[Int]),
        KnownValue(1, classOf[Int]),
        KnownValue(2, classOf[Int]),
        KnownValue(3, classOf[Int]),
        KnownValue(4, classOf[Int]))
    }
    "DUP2_X1" >> {
      val c = exec(new InsnNode(Opcodes.DUP2_X1))
      c.stack must_== List(
        KnownValue(1, classOf[Int]),
        KnownValue(2, classOf[Int]),
        KnownValue(3, classOf[Int]),
        KnownValue(1, classOf[Int]),
        KnownValue(2, classOf[Int]),
        KnownValue(4, classOf[Int]))
    }
    "DUP2_X2" >> {
      val c = exec(new InsnNode(Opcodes.DUP2_X2))
      c.stack must_== List(
        KnownValue(1, classOf[Int]),
        KnownValue(2, classOf[Int]),
        KnownValue(3, classOf[Int]),
        KnownValue(4, classOf[Int]),
        KnownValue(1, classOf[Int]),
        KnownValue(2, classOf[Int]))
    }
    "SWAP" >> {
      val c = exec(new InsnNode(Opcodes.SWAP))
      c.stack must_== List(
        KnownValue(2, classOf[Int]),
        KnownValue(1, classOf[Int]),
        KnownValue(3, classOf[Int]),
        KnownValue(4, classOf[Int]))
    }
  }
}
