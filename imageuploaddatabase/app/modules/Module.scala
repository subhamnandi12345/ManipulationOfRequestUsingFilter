package modules
import com.google.inject.AbstractModule
import repositories.ImageRepository

class Module extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[ImageRepository]).asEagerSingleton()
  }
}

