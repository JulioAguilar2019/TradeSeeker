package sv.edu.catolica.project_final.Models;

import java.util.List;

public class HomeModel {
    private List<PopularLocationModel> popular_locations;
    private List<WorkModel> last_works;

    public HomeModel(List<PopularLocationModel> popular_locations, List<WorkModel> last_works) {
        this.popular_locations = popular_locations;
        this.last_works = last_works;
    }

    public List<PopularLocationModel> getPopular_locations() {
        return popular_locations;
    }

    public void setPopular_locations(List<PopularLocationModel> popular_locations) {
        this.popular_locations = popular_locations;
    }

    public List<WorkModel> getLast_works() {
        return last_works;
    }

    public void setLast_works(List<WorkModel> last_works) {
        this.last_works = last_works;
    }
}
