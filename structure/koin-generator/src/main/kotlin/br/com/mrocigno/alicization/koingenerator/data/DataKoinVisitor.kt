package br.com.mrocigno.alicization.koingenerator.data

import br.com.mrocigno.alicization.koingenerator.asOutputStreamWriter
import br.com.mrocigno.alicization.koingenerator.writeLine
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid
import java.io.OutputStreamWriter

class DataKoinVisitor(
    private val environment: SymbolProcessorEnvironment,
    private val logger: OutputStreamWriter
) : KSVisitorVoid() {

    private lateinit var filePackage: String
    private lateinit var className: String
    private lateinit var file: OutputStreamWriter

    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
        filePackage = classDeclaration.packageName.asString()
        className = classDeclaration.simpleName.asString()
        val fileName = "${className}KoinAdapter"

        logger.writeLine("========= Generating file $fileName =========")
        logger.writeLine(filePackage)

        file = environment.codeGenerator.createNewFile(
            dependencies = Dependencies(false),
            packageName = filePackage,
            fileName = fileName
        ).asOutputStreamWriter()
        create()
    }

    private fun create() {
        file.write("""
            package $filePackage
            
            import org.koin.core.definition.Definition
            import org.koin.core.module.Module
            import org.koin.core.qualifier.named
            import org.koin.dsl.module
            import org.koin.core.scope.Scope
            
            private const val LOCAL_NAMED = "${className}Local"
            private const val REMOTE_NAMED = "${className}Remote"
            
            fun Module.declare${className}Remote(definition: Definition<$className>) =
                single<$className>(named(REMOTE_NAMED), false, definition)
            
            fun Scope.get${className}Remote() =
                get<$className>(named(REMOTE_NAMED))
            
            fun Module.declare${className}Local(definition: Definition<$className>) =
                single<$className>(named(LOCAL_NAMED), false, definition)
            
            fun Scope.get${className}Local() =
                get<$className>(named(LOCAL_NAMED))
                
        """.trimIndent())
        file.close()
    }
}