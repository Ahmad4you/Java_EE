package wolkenag.frontcontroller;



public class ControllerFactory {

	public ControllerFactory() {

	}

	public Controller getController(final String controllerName) {

		switch (controllerName) {
		case "registrierung":
			return new RegistrierungController();
		case "startseite":
			return new StartseiteController();
		case "terminbuchung1":
			return new Terminbuchung1Controller();
		case "kalender":
			return new KalenderController();
		case "terminbuchung2":
			return new Terminbuchung2Controller();
		case "terminbuchung3":
			return new Terminbuchung3Controller();
		case "terminbuchung4":
			return new Terminbuchung4Controller();
		case "news":
			return new NewsController();
		case "about":
			return new AboutController();
		case "blog":
			return new BlogController();
		default:
		}
		return null;
	}
}
