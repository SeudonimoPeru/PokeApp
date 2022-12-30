# appNoticias


VIEW:     En esta capa se crea  todo lo relacionado al manejo de las vistas (activity , viewmodel, adapter).
          El viewmodel comunica con el caso de uso (domain) para poder traer la data ya sea de pokeapi o realdata de firebase, en un primer 
          momento intenta trae las regiones de pokeapi. El usuario puede ver  sus grupos creador  ( traidos de realdata) , tambien puede  visualizar los grupos en cada             region e  identificar cual es suyo


DOMAIN:   Se crea los casos de uso, en este caso tanto de realdata de firebase y pokeapi


DATA:     En esta capa nos encargamos de construir todo lo referente sobre el acceso a los datos..
          
            . Se crea los data class referente a la estructura del json que nos trae la url brindada
           
            .Se crea los repositorios  ( online y local)
            
            
¿por que se usa tres capas? 

Se proyecta que el proyecto puede ser  en un futuro mas estructurado por el cual se sigue las recomendaciones de Google de usarlas, en caso se indique que será de corto alcance se podría modificar a dos capaz
¿puntos de mejora?

. Utilizar  inyección a futuro
. Utilizar navigation component y manejarlo por fragment
. Implementar databinding y acortar código
