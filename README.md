# commons
commonly used classes and functionality

# Releases

__4.0.1__ CVE-2019-14379, CVE-2019-14439

__4.0.0__ refreshToken aus Client-Authentisierung entfernt. Es genügt ein kurzzeitig gültiges accessToken, welches über Server-Server-Kommunikation mittels ClientSecrets rechtzeitig erneuert wird. Außerdem: dynamic config simplified.

__3.0.0__ Nicht abwärtskompatible Änderungen: Client-Authentisierung mittels server-server-Kommunikation. Die Client-Secrets werden gegen ein accessToken eingetauscht, das im Frontend benutzt werden kann, so dass dieses keine Client-Secrets mehr benötigt.

__2.0.4__ jwtAuth für alle projekte

__2.0.3__ convenience methods for responsePayload

__2.0.2__ logging changed

__2.0.1__ date format strings fixed: hours 0-23

__2.0.0__ switch to java 11 wothout code changes

__1.1.1:__ Höhere Versionen von jackson-databind und jwt-java, OWASP dependency-check-plugin aktiviert, surefire-tests mit JUnit5 aktivert

__1.1.0:__ Serialisierung von geschachtelten objekten funktioniert mit resteasy anscheinend nicht: daher eigener MessageBodyWriter

__1.0.0:__ main functionality for authprovider and checklistenserver

# Development tools

Check for newer versions of dependencies with:

	mvn versions:display-dependency-updates

