/**
 * This package is licensed under the Apache License, Version 2.0.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.home.metrics;

import java.util.Set;

//import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
//import org.eclipse.microprofile.openapi.annotations.info.Info;
//import org.eclipse.microprofile.openapi.annotations.servers.Server;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;




//@OpenAPIDefinition(info = @Info(
//        title = "Test OpenAPI",
//        version = "1.0.0"     
//),
//        servers = {
//            @Server(url = "/openapi", description = "localhost")
//        }
//)
@ApplicationScoped
@ApplicationPath("/")
public class MpMetricsApplication extends Application {
	
	/*
	 * Diese Methode gibt die Klassen zurück, die als Ressourcen oder Provider in der Jakarta EE Anwendung verwendet. 
	 * Hier wird GreetResource registriert
	 * 
	 */

    @Override
    public Set<Class<?>> getClasses() {
        return Set.of(MpMetricsResource.class);
    }
}
