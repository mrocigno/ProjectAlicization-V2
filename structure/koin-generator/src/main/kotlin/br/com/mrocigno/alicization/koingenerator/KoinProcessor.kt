package br.com.mrocigno.alicization.koingenerator

import br.com.mrocigno.alicization.koingenerator.data.DataKoinAdapter
import br.com.mrocigno.alicization.koingenerator.data.DataKoinVisitor
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated
import java.io.ByteArrayOutputStream
import java.io.OutputStreamWriter

class KoinProcessor(
    private val environment: SymbolProcessorEnvironment
) : SymbolProcessor {

    private var invoked = false

    override fun process(resolver: Resolver): List<KSAnnotated> {
        if (invoked) return emptyList()

        val annotated = resolver.getSymbolsWithAnnotation(DataKoinAdapter::class.java.name)

        runCatching {
            environment.codeGenerator
                .createNewFile(Dependencies(false), "", "log", "txt")
                .asOutputStreamWriter()
        }.getOrElse {
            OutputStreamWriter(ByteArrayOutputStream())
        }.use { log ->
            annotated.forEach { it.accept(DataKoinVisitor(environment, log), Unit) }
        }

        invoked = true
        return annotated.toList()
    }
}