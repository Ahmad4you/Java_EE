package orderedObserver;

import jakarta.annotation.Priority;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.interceptor.Interceptor;

/**
 * 
 * Verwendung von geordneten Observern
 * Microservices-Architekturen: In Microservices können Events verwendet werden, um lose gekoppelte Kommunikation zwischen verschiedenen Services 
 * zu ermöglichen. Geordnete Observer können sicherstellen, dass bestimmte Verarbeitungsschritte in der richtigen Reihenfolge ausgeführt werden.
 * Geschäftsprozess-Management: Bei der Modellierung komplexer Geschäftsprozesse können Events verwendet werden, 
 * um den Fortschritt durch verschiedene Stadien zu signalisieren. Die Priorisierung von Observern kann helfen, 
 * die richtige Reihenfolge von Aktionen sicherzustellen.
 * Workflow-Systeme: In Workflow-Engines können Events den Übergang zwischen verschiedenen Zuständen oder Schritten eines Workflows signalisieren. 
 * Geordnete Observer können verwendet werden, um sicherzustellen, dass bestimmte Aktionen vor oder nach anderen ausgeführt werden.
 * Logging und Auditing: Events können verwendet werden, um wichtige Systemereignisse zu protokollieren. Mit priorisierten Observern können Sie sicherstellen, 
 * dass bestimmte Logging- oder Auditing-Schritte vor oder nach der Hauptverarbeitung erfolgen.
 * Transaktionsmanagement: In komplexen Transaktionsszenarien können Events verwendet werden, 
 * um verschiedene Phasen einer Transaktion zu signalisieren. Observer mit unterschiedlichen Prioritäten können für Vor- und Nachbearbeitung, 
 * Validierung oder Rollback-Operationen verwendet werden.
 * Plugin-Systeme: In erweiterbaren Anwendungen können Events verwendet werden, um Plugins zu benachrichtigen. 
 * Die Priorisierung kann sicherstellen, dass bestimmte Plugins vor oder nach anderen ausgeführt werden.
 * Reactive Programming: 
 * Konfigurationsmanagement: Bei der Initialisierung von Anwendungen können Events verwendet werden, 
 * um verschiedene Konfigurationsschritte zu koordinieren, wobei die Reihenfolge durch priorisierte Observer gesteuert wird.
 * Datensynchronisation: In verteilten Systemen können Events verwendet werden, um Änderungen zu propagieren. Die Priorisierung kann helfen, 
 * die Konsistenz über mehrere Systeme hinweg zu gewährleisten.
 * Testautomatisierung: In Testumgebungen können Events und geordnete Observer verwendet werden, um komplexe Testszenarien zu modellieren und 
 * die richtige Reihenfolge von Testschritten sicherzustellen.
 * 
 * @author Ahmad Alrefai
 */
class MyEvent {

	private final String value;

	public MyEvent(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}

public class OrderedObserver {

	public static void main(String[] args) {
		SeContainer container = SeContainerInitializer.newInstance().initialize();
		container.getBeanManager().fireEvent(new MyEvent("event -> " + System.currentTimeMillis()));
	}

	public void thisEventBefore(@Observes @Priority(Interceptor.Priority.APPLICATION - 200) MyEvent event) {
		System.out.println("thisEventBefore: " + event.getValue());
	}

	public void thisEventAfter(@Observes @Priority(Interceptor.Priority.APPLICATION + 200) MyEvent event) {
		System.out.println("thisEventAfter: " + event.getValue());
	}

}
