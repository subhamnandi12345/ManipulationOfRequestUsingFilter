import javax.inject.Inject
import play.api.http.DefaultHttpFilters

class Filters @Inject() (headerManipulationFilter: HeaderManipulationFilter) extends DefaultHttpFilters(headerManipulationFilter)
