import java.util.List;
import java.util.Map;

public class ThreatreController extends TheatreController {
    public ThreatreController() {
        super();
    }

    public ThreatreController(Map<Location, List<Theatre>> theatresByLocation, Map<Integer, Theatre> theatresById) {
        super(theatresByLocation, theatresById);
    }
}
