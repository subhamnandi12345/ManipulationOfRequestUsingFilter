package modules
// app/modules/MyModule.scala

import com.google.inject.AbstractModule
import filters.MyFilters
// MyModule.scala
import com.google.inject.AbstractModule

class MyModule extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[MyFilters]).asEagerSingleton()
  }
}


