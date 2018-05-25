-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cvsystem
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.26-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

create database cvsystem;
use cvsystem;

DROP TABLE IF EXISTS `compra_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compra_producto` (
  `id_compra_producto` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_compra` date NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_producto_inventario` int(11) NOT NULL,
  PRIMARY KEY (`id_compra_producto`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_producto_inventario` (`id_producto_inventario`),
  CONSTRAINT `compra_producto_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`),
  CONSTRAINT `compra_producto_ibfk_2` FOREIGN KEY (`id_producto_inventario`) REFERENCES `producto_inventario` (`id_producto_inventario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra_producto`
--

LOCK TABLES `compra_producto` WRITE;
/*!40000 ALTER TABLE `compra_producto` DISABLE KEYS */;
INSERT INTO `compra_producto` VALUES (1,'2017-09-15',1,2),(2,'2017-11-25',4,5),(3,'2018-01-19',2,7),(4,'2018-02-10',2,9);
/*!40000 ALTER TABLE `compra_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compraventa`
--

DROP TABLE IF EXISTS `compraventa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compraventa` (
  `id_compraventa` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(1000) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `interesAnual` float DEFAULT NULL,
  PRIMARY KEY (`id_compraventa`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `compraventa_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compraventa`
--

LOCK TABLES `compraventa` WRITE;
/*!40000 ALTER TABLE `compraventa` DISABLE KEYS */;
INSERT INTO `compraventa` VALUES (1,'Compraventa Rokefeller','Compra, vente y empeña lo que necesites, aqui en Rokefeller','Calle 60 norte N° 24 - 21','2486819',5,28),(2,'LaHome2','Calidad y rapidez, es nuestro lema','Calle 55b norte N° 24 - 18','4681598',6,27),(3,'CasaComercial Fargon','Nuestra compraventa es de la mejor calidad','Avenida 65c oeste No 54-15','6478979',7,27.5),(4,'Compraventa Lakers','Lak lak lakker compra ya!','Calle 60 norte No 24-35','6489897',8,29),(5,'Compraventa Lakers','Lak lak lakker compra ya!','Carrera 13 No 15-35','6188948',8,26);
/*!40000 ALTER TABLE `compraventa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto_empeno`
--

DROP TABLE IF EXISTS `producto_empeno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producto_empeno` (
  `id_producto_empeno` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_inicio` date NOT NULL,
  `fecha_final` date NOT NULL,
  `estado` varchar(45) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_compraventa` int(11) NOT NULL,
  `precioapagar` float DEFAULT NULL,
  `interes` float DEFAULT NULL,
  `dias` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_producto_empeno`),
  KEY `id_producto` (`id_producto`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_compraventa` (`id_compraventa`),
  CONSTRAINT `producto_empeno_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`),
  CONSTRAINT `producto_empeno_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`),
  CONSTRAINT `producto_empeno_ibfk_3` FOREIGN KEY (`id_compraventa`) REFERENCES `compraventa` (`id_compraventa`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto_empeno`
--

LOCK TABLES `producto_empeno` WRITE;
/*!40000 ALTER TABLE `producto_empeno` DISABLE KEYS */;
INSERT INTO `producto_empeno` VALUES (1,'2017-12-22','2018-01-18','reclamado',4,1,4,NULL,NULL,NULL),(2,'2018-02-18','2018-03-18','pendiente',9,3,1,NULL,NULL,NULL);
/*!40000 ALTER TABLE `producto_empeno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto_inventario`
--

DROP TABLE IF EXISTS `producto_inventario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producto_inventario` (
  `id_producto_inventario` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_ingreso` date NOT NULL,
  `estado` varchar(20) DEFAULT NULL,
  `id_producto` int(11) NOT NULL,
  `id_compraventa` int(11) NOT NULL,
  PRIMARY KEY (`id_producto_inventario`),
  KEY `id_producto` (`id_producto`),
  KEY `id_compraventa` (`id_compraventa`),
  CONSTRAINT `producto_inventario_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`),
  CONSTRAINT `producto_inventario_ibfk_2` FOREIGN KEY (`id_compraventa`) REFERENCES `compraventa` (`id_compraventa`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto_inventario`
--

LOCK TABLES `producto_inventario` WRITE;
/*!40000 ALTER TABLE `producto_inventario` DISABLE KEYS */;
INSERT INTO `producto_inventario` VALUES (1,'2017-08-15','disponible',1,1),(2,'2017-09-15','separado',2,2),(3,'2017-10-29','almacenado',3,1),(4,'2017-11-03','disponible',5,1),(5,'2017-11-25','separado',6,5),(6,'2017-12-15','almacenado',7,5),(7,'2018-01-19','comprado',8,4),(8,'2018-01-20','disponible',10,3),(9,'2018-02-10','comprado',11,3),(10,'2018-02-20','disponible',12,4);
/*!40000 ALTER TABLE `producto_inventario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos` (
  `id_producto` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(1000) NOT NULL,
  `categoria` varchar(45) NOT NULL,
  `precio` float NOT NULL,
  `fotos` varchar(45) DEFAULT NULL,
  `id_Compraventa` int(11) DEFAULT NULL,
  `estatus` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_producto`),
  KEY `id_Compraventa` (`id_Compraventa`),
  CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`id_Compraventa`) REFERENCES `compraventa` (`id_compraventa`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'IphoneX','Excelente celular gama alta ultra veloz','tecnologia',4200000,'resources/img/productos/Producto(1)/',1,'Disponible'),(2,'Apple Series 3','Marca Apple, gran relog avanzado','tecnologia',280000,'resources/img/productos/Producto(2)/',1,'Disponible'),(3,'Refrigerador nak','Gran refrigerador, con acondicionador incluido','domesticos',455000,'resources/img/productos/Producto(3)/',1,'Disponible'),(4,'Balon nike','Balon rojo con negro marca nike','deportes',35000,'resources/img/productos/Producto(4)/',1,'Disponible'),(5,'Bicicleta','Bicicleta color negro, en excelente estado','deportes',180000,'resources/img/productos/Producto(5)/',2,'Separado'),(6,'Parlantes Genius','Parlantes negros con un muy buen sonido','tecnologia',50000,'resources/img/productos/Producto(6)/',2,'Separado'),(7,'Cadena Dorada','Brillante cadena de color dorado, a muy bajo precio','joyeria',10000,'resources/img/productos/Producto(7)/',2,'Searado'),(8,'Bicicleta Montana','Con muy buen estado, bicicleta marca montana','deportes',155000,'resources/img/productos/Producto(8)/',2,'Separado'),(9,'Huawei','Celular con 8G de almacenamiento, muy rapido','tecnologia',570000,'resources/img/productos/Producto(9)/',3,'Separado'),(10,'Pintura LaYampia','Marco de pintura, de el gran pintor Jaime Yortuno','arte',58000,'resources/img/productos/Producto(10)/',3,'Disponible'),(11,'Impresora Cannon','Impresora marca cannon con muy buena calidad de impresión','tecnologia',185000,'resources/img/productos/Producto(11)/',3,'Disponible'),(12,'Gorra Nike','Gorra de color negro con marca nike','deportes',25000,'resources/img/productos/Producto(12)/',3,'Disponible'),(24,'Bicicleta Cannondale','Bicicleta Todo Terreno','Deportes',1500000,'/CVSystem/archivos/Bicicleta1.jpg',4,'Disponible'),(25,'Anillo en Oro ','Anillo en Oro 24Kilates','tecnologia',250000,'/CVSystem/archivos/Anillo.jpg',4,'Disponible'),(26,'Anillo en Oro ','Anillo en Oro 24Kilates','joyeria',250000,'/CVSystem/archivos/Anillo.jpg',4,'Disponible'),(27,'Anillo en Oro ','Anillo en Oro 24Kilates','joyeria',250000,'/CVSystem/archivos/Anillo.jpg',4,'Disponible'),(28,'Reloj Cassino','Reloj de oro casiino','Relojeria',450000,'/CVSystem/archivos/reloj.jpg',2,'Separado'),(33,'Reloj Cassino','Reloj de oro casiino','Relojeria',450000,NULL,2,'Separado'),(34,'Bicicleta Cannondale','Bicicleta Todo Terreno','Deportes',4654650,'/CVSystem/archivos/Bicicleta3.jpg',2,'Disponible'),(70,'reloj morter','reloj deportivo morter','Relojeria',950000,'/CV-System/imagenes/descarga.jpg',2,'Disponible'),(71,'','','',0,NULL,NULL,''),(72,'Laptop Asus','Computador Portatil Asus intel core i7 4ta generacion','Tecnologia',1855000,'/CV-System/imagenes/Portatil Asus.jpg',2,NULL),(73,'Laptop Asus','Computador Portatil Asus intel core i7 4ta generacion','Tecnologia',1855000,'/CV-System/imagenes/Portatil Asus.jpg',2,NULL),(74,'Laptop Asus','Computador Portatil Asus intel core i7 4ta generacion','Tecnologia',1855000,'/CV-System/imagenes/Portatil Asus.jpg',2,NULL),(75,'Laptop Asus','Computador Portatil Asus intel core i7 4ta generacion','Tecnologia',1855000,'/CV-System/imagenes/Portatil Asus.jpg',2,NULL),(76,'Laptop Asus','Computador Portatil Asus intel core i7 4ta generacion','Tecnologia',1855000,'/CV-System/imagenes/Portatil Asus.jpg',2,'Disponible'),(77,'Collar','Collar con esmeralda','Joyeria',8957460,'/CV-System/imagenes/Collar.jpg',2,'Disponible');
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitud_compraventa`
--

DROP TABLE IF EXISTS `solicitud_compraventa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `solicitud_compraventa` (
  `id_solicitud_compraventa` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(45) NOT NULL,
  `categoria` varchar(45) NOT NULL,
  `descripcion` varchar(1000) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `precio` float NOT NULL,
  `fotos` varchar(45) NOT NULL,
  `fecha_envio` date NOT NULL,
  `respuesta_msg` varchar(1000) DEFAULT NULL,
  `respuesta` varchar(45) DEFAULT NULL,
  `id_usuario_servicios` int(11) DEFAULT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_compraventa` int(11) NOT NULL,
  PRIMARY KEY (`id_solicitud_compraventa`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_compraventa` (`id_compraventa`),
  CONSTRAINT `solicitud_compraventa_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`),
  CONSTRAINT `solicitud_compraventa_ibfk_2` FOREIGN KEY (`id_compraventa`) REFERENCES `compraventa` (`id_compraventa`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitud_compraventa`
--

LOCK TABLES `solicitud_compraventa` WRITE;
/*!40000 ALTER TABLE `solicitud_compraventa` DISABLE KEYS */;
INSERT INTO `solicitud_compraventa` VALUES (1,'Apple Series 3','tecnologia','Vendo excelente reloj de marca apple','venta',200000,'https://cvsystem.com/fotos/a58s6x1/','2017-08-10','','',0,2,2),(2,'Balon nike','deportes','Empeño un balon nike a bajo precio','empeño',35000,'https://cvsystem.com/fotos/6a5s4da/','2017-12-20','Te acepto el empeño, traelo cuando puedas','aceptada',10,1,4),(3,'Celular ZTE','tecnologia','Vendo excelente celular muy rapido y con buena capacidad','venta',1000000,'https://cvsystem.com/fotos/z6asd5f/','2018-01-02','No te lo acepto, está muy caro','rechazada',10,3,3),(4,'Huawei','tecnologia','Estoy empeñando un huawei con muy buena resolución','empeño',570000,'https://cvsystem.com/fotos/h1463as/','2018-02-15','Ok te lo acepto, te espero','aceptada',9,3,1),(5,'Monitor HP','tecnologia','Estoy empeñando un monitor marca HP de 32 pulgadas','empeño',325000,'https://cvsystem.com/fotos/m1sd1f5/','2018-02-20','csvfsdgfshfh','Respondido',1,1,2),(6,'Anillo de bronce','joyeria','Vendo un anillo de bronce a muy bajo precio','venta',20000,'https://cvsystem.com/fotos/a64nsd4/','2018-02-22','','',0,4,2),(8,'Mouse','Technology','Bonito','empeño',2342340,'https://cvsystem.com/fotos/fs31a5d/','2018-04-12','Hola',NULL,1,12,2),(9,'sdfsdf','dsfdsf','sdfsdf','sdfsdf',13546,'dgsdgsdg','2018-04-06',NULL,NULL,0,12,2),(10,'wetwet','gw','gwgwe','gwegwe',1234,'gdsgsg','2018-04-16','estoy interesado en el producto.\r\n\r\ncordialmente Jorge Amado',NULL,1,1,2);
/*!40000 ALTER TABLE `solicitud_compraventa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitud_domicilio`
--

DROP TABLE IF EXISTS `solicitud_domicilio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `solicitud_domicilio` (
  `id_solicitud_domicilio` int(11) NOT NULL AUTO_INCREMENT,
  `direccion` varchar(45) NOT NULL,
  `celular` varchar(10) NOT NULL,
  `estado` varchar(45) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  PRIMARY KEY (`id_solicitud_domicilio`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_producto` (`id_producto`),
  CONSTRAINT `solicitud_domicilio_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`),
  CONSTRAINT `solicitud_domicilio_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitud_domicilio`
--

LOCK TABLES `solicitud_domicilio` WRITE;
/*!40000 ALTER TABLE `solicitud_domicilio` DISABLE KEYS */;
INSERT INTO `solicitud_domicilio` VALUES (1,'Calle 25 sur N° 24 - 21','3196445619','pendiente',1,2),(2,'Calle 35a norte N° 24 - 15','3205879468','completa',2,8);
/*!40000 ALTER TABLE `solicitud_domicilio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `tipo_documento` varchar(45) NOT NULL,
  `numero_documento` varchar(20) NOT NULL,
  `edad` int(11) NOT NULL,
  `sexo` varchar(20) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `celular` varchar(10) NOT NULL,
  `correo_electronico` varchar(45) NOT NULL,
  `contrasena` varchar(45) NOT NULL,
  `estado` varchar(20) NOT NULL,
  `rol` varchar(20) NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Luis Esteban genial','Garzon Ayala','cedula de ciudadania','646546465',22,'masculino','Calle 25 sur N° 24 - 21','2797645','3196445619','berfomed@gmail.com','asdf1','activo','cliente'),(2,'Jorge Salomon ','Martin Prado cool','cedula de ciudadania','849416897',35,'masculino','Avenida Carrera 45 N° 35-28','4680465','3205879468','jaamado362@misena.edu.co','a6s5d4','activo','cliente'),(3,'Devora Meltrozon','Parada Largin','cedula de extranjera','1064897941',19,'masculino','Carrera 25 N° 21-35','4680465','3228447068','devoron98@gmail.com','987645321','bloqueado','cliente'),(4,'Laura Sofia','Ayala Poraya','pasaporte','578554984',25,'femenino','Diagonal 22','2791535','3221954865','lau@gmail.com','laurita','activo','cliente'),(5,'Jorge Palacio','Martin Garzon','cedula de ciudadania','648494654',35,'masculino','Calle 84b sur','2648949','3204789765','Bartholomew@gmail.com','clave','activo','gerente'),(6,'Emanuel Emilio','Rodriguez Andino','cedula de ciudadania','1064894615',22,'masculino','Avenida Carrera 46 norte','4684684','3194864898','Emiliano@gmail.com','qwer321','activo','gerente'),(7,'Sandra Cecilia','Ariza Gomez','cedula extranjera','1689798989',30,'femenino','Calle 85b norte','6846148','3150579865','sandrariza@gmail.com','passlog','activo','gerente'),(8,'Karen Jirleth','Paron Bentran','cedula de ciudadania','564897987',35,'femenino','Avenida 14 sur N° 24-25','6848979','3208554978','Kathe@gmail.com','karenJi','activo','gerente'),(9,'Ivan Dario','Benito Camelas','pasaporte','120646544',28,'masculino','Diagonal 48c oeste','4657898','3106489845','Benitocamelo@gmail.com','benitodito','activo','servicios'),(10,'Jeffer Alfonso','Rodriguez Manzanare','cedula de ciudadania','654987979',24,'masculino','Calle 13 sur','6489797','3208271118','Jefferon@gmail.com','contraseña','activo','servicios'),(11,'Yampier Esteban','Anda Ardido','cedula de ciudadania','105648979',25,'masculino','Calle 87 este','8468794','3205879486','Yampu@gmail.com','xlxl25','activo','administrador'),(12,'Jeffer Arley','Fagua Piña','cedula de ciudadania','1007719431',24,'masculino','Calle40a sur No 24 - 21','2797700','3196419019','jafp93@hotmail.com','123456','activo','cliente'),(16,'Luis Esteban','Garzon Ayala','cedula de ciudadania','646546465',22,'masculino','Calle 25 sur N° 24 - 21','2797645','3196445619','berfomed@gmail.com','rrrrrrr','activo','cliente'),(17,'Luis Esteban','Garzon Ayala','cedula de ciudadania','646546465',22,'masculino','Calle 25 sur N° 24 - 21','2797645','3196445619','berfomed@gmail.com','rrrrrrr','activo','cliente'),(18,'Luis Esteban','Garzon Ayala','cedula de ciudadania','646546465',22,'masculino','Calle 25 sur N° 24 - 21','2797645','3196445619','berfomed@gmail.com','rrrrrrr','activo','cliente'),(19,'Jorge Salomon','Martin Prado cool','cedula de ciudadania','849416897',32,'masculino','Avenida Carrera 45 N° 35-28','4680465','3205879468','Pradin@gmail.com','a6s5d4','activo','cliente'),(20,'Elber Dario','Beltran Beltran','Cedula de Ciudadania','134685135',18,'masculino','calle Dario','1621654','31025432','dario@gmail','454dsf','activo','administrador');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'cvsystem'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-23 20:46:59
