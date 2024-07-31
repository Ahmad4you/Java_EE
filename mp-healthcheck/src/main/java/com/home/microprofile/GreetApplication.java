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
package com.home.microprofile;

import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;



@ApplicationScoped
@ApplicationPath("/")
public class GreetApplication extends Application {
	
	/*
	 * Diese Methode gibt die Klassen zurück, die als Ressourcen oder Provider in der Jakarta EE Anwendung verwendet. 
	 * Hier wird GreetResource registriert
	 * 
	 */

    @Override
    public Set<Class<?>> getClasses() {
        return Set.of(GreetResource.class);
    }
}
