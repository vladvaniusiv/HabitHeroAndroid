package com.example.habithero.core.resources

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.habithero.core.ui.components.BottomBar

@Composable
fun TermsAndConditionsText() {
    Column {
        Text("📄 TÉRMINOS Y CONDICIONES DE USO", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text("HabitHero", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp))
        Text("1. Información general", style = MaterialTheme.typography.titleMedium)
        Text(
            "HabitHero es una aplicación móvil y web orientada a fomentar la creación y mantenimiento de hábitos positivos mediante técnicas de gamificación. Su objetivo es promover la motivación personal, el bienestar y la constancia a través del seguimiento de hábitos, recompensas virtuales y visualización de progreso.\n\nEl uso de la aplicación implica la aceptación plena de los presentes Términos y Condiciones.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text("2. Usuarios y registro", style = MaterialTheme.typography.titleMedium)
        Text(
            "Para utilizar determinadas funcionalidades, el usuario debe registrarse proporcionando información veraz y actualizada. El usuario es responsable de mantener la confidencialidad de sus credenciales de acceso y del uso que se realice desde su cuenta.\n\nHabitHero se reserva el derecho de suspender o eliminar cuentas que hagan un uso indebido del sistema.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text("3. Uso permitido", style = MaterialTheme.typography.titleMedium)
        Text(
            "El usuario se compromete a:\n• Utilizar la aplicación únicamente con fines personales, educativos o de mejora personal.\n• No introducir información falsa, ofensiva o ilegal.\n• No intentar acceder a áreas restringidas, sistemas o datos de otros usuarios.\n\nQueda prohibido el uso de HabitHero para actividades fraudulentas, ilegales o que puedan dañar la aplicación o a terceros.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text("4. Funcionalidades y disponibilidad", style = MaterialTheme.typography.titleMedium)
        Text(
            "HabitHero ofrece funcionalidades como:\n• Registro y gestión de hábitos.\n• Seguimiento del progreso personal o grupal.\n• Sistema de gamificación con recompensas virtuales.\n• Recordatorios automáticos y estadísticas.\n\nAlgunas funciones pueden variar o ampliarse con el tiempo como parte del desarrollo continuo del proyecto.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text("5. Panel administrativo", style = MaterialTheme.typography.titleMedium)
        Text(
            "La versión web puede incluir un panel administrativo destinado a profesionales (coaches, psicólogos u otros perfiles autorizados) para el seguimiento del progreso de los usuarios y la generación de informes personalizados, siempre conforme a los permisos otorgados.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text("6. Propiedad intelectual", style = MaterialTheme.typography.titleMedium)
        Text(
            "Todos los contenidos de la aplicación (diseño, código, logotipos, textos y gráficos) son propiedad de los responsables del proyecto HabitHero o se utilizan con autorización, quedando protegidos por la legislación vigente en materia de propiedad intelectual.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text("7. Responsabilidad", style = MaterialTheme.typography.titleMedium)
        Text(
            "HabitHero es una herramienta de apoyo para la mejora de hábitos personales. No sustituye asesoramiento médico, psicológico o profesional. El uso de la aplicación se realiza bajo la responsabilidad del usuario.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text("8. Modificaciones", style = MaterialTheme.typography.titleMedium)
        Text(
            "HabitHero se reserva el derecho de modificar estos Términos y Condiciones en cualquier momento. Las modificaciones serán comunicadas a los usuarios a través de la aplicación o la web.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text("9. Legislación aplicable", style = MaterialTheme.typography.titleMedium)
        Text(
            "Estos términos se rigen por la legislación vigente aplicable en el ámbito académico y tecnológico correspondiente.",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun PrivacyPolicyText() {
    Column {
        Text("🔒 POLÍTICA DE PRIVACIDAD", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text("HabitHero", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp))
        Text("1. Introducción", style = MaterialTheme.typography.titleMedium)
        Text(
            "HabitHero respeta la privacidad de los usuarios y se compromete a proteger sus datos personales. Esta Política de Privacidad explica cómo se recopila, utiliza y gestiona la información proporcionada por los usuarios.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text("2. Datos recopilados", style = MaterialTheme.typography.titleMedium)
        Text(
            "HabitHero puede recopilar los siguientes datos:\n• Datos de registro: nombre, correo electrónico y contraseña.\n• Datos de uso: hábitos creados, progreso, estadísticas y frecuencia de uso.\n• Datos técnicos: información básica del dispositivo y registros de actividad.\n\nNo se recopilan datos especialmente sensibles ni información médica.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text("3. Finalidad del tratamiento", style = MaterialTheme.typography.titleMedium)
        Text(
            "Los datos personales se utilizan para:\n• Gestionar el acceso y la autenticación de usuarios.\n• Permitir el registro y seguimiento de hábitos.\n• Mostrar estadísticas y evolución del progreso.\n• Enviar recordatorios y notificaciones.\n• Mejorar la experiencia de usuario y el funcionamiento de la aplicación.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text("4. Conservación de los datos", style = MaterialTheme.typography.titleMedium)
        Text(
            "Los datos se conservarán mientras el usuario mantenga su cuenta activa o durante el tiempo necesario para cumplir con las finalidades descritas.\n\nEl usuario puede solicitar la eliminación de su cuenta y de sus datos asociados.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text("5. Compartición de datos", style = MaterialTheme.typography.titleMedium)
        Text(
            "HabitHero no comparte datos personales con terceros, salvo cuando sea necesario para el funcionamiento técnico del sistema (por ejemplo, servicios en la nube) o por obligación legal.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text("6. Seguridad", style = MaterialTheme.typography.titleMedium)
        Text(
            "Se aplican medidas técnicas y organizativas razonables para proteger los datos personales frente a accesos no autorizados, pérdidas o usos indebidos.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text("7. Derechos del usuario", style = MaterialTheme.typography.titleMedium)
        Text(
            "El usuario puede ejercer los siguientes derechos:\n• Acceso a sus datos personales.\n• Rectificación de datos inexactos.\n• Eliminación de sus datos.\n• Limitación u oposición al tratamiento.\n\nLas solicitudes pueden realizarse a través de los canales habilitados en la aplicación o plataforma web.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text("8. Cambios en la política de privacidad", style = MaterialTheme.typography.titleMedium)
        Text(
            "Esta Política de Privacidad puede actualizarse para reflejar cambios en la aplicación o en la normativa aplicable. Se informará a los usuarios de cualquier modificación relevante.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text("9. Aceptación", style = MaterialTheme.typography.titleMedium)
        Text(
            "El uso de HabitHero implica la aceptación de esta Política de Privacidad.",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TermsAndConditionsTextPreview() {
    MaterialTheme {
        //TermsAndConditionsText()
        PrivacyPolicyText()
    }
}