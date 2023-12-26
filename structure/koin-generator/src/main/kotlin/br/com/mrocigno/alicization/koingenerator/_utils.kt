package br.com.mrocigno.alicization.koingenerator

import java.io.OutputStream
import java.io.OutputStreamWriter

internal fun OutputStreamWriter.writeLine(str: String) =
    write("$str\n")

internal fun OutputStream.asOutputStreamWriter() =
    OutputStreamWriter(this)