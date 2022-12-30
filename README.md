# appNoticias


VIEW:     En esta capa se crea  todo lo relacionado al manejo de las vistas (activity , viewmodel, adapter).
          El viewmodel comunica con el caso de uno NewsUseCase (domain) para poder traer las noticias, en un primer 
          momento intenta traer del repositorio online. Si logra traer las noticias actualiza bd y muestra, de lo contrario utiliza 
          repositorio local. Por otro lado,  al selecciona un item de recyclerview nos manda a la webview y se actualiza el flag de visibilidad 
          en la bd para que esta ya no se muestre en la lista


DOMAIN:   Se crea el caso de uso, para este contexto solo existe el traer las noticias (NewsUseCase), en la cual se le incluye
          los dos repos (online y local). A través de este caso de uso es que el viewmodel (view) se comunicará con la capa de datos.


DATA:     En esta capa nos encargamos de construir todo lo referente sobre el acceso a los datos..
            . Se crea la estructura de las tablas necesarias en estado caso solo el de noticia
            . Se crea los data class referente a la estructura del json que nos trae la url brindada
            . Se crea mappers para manejo de la entidad (RoomNews) con el  data class(News)
            .Se crea los repositorios  ( online y local)
