-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           5.7.14-log - MySQL Community Server (GPL)
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Copiando estrutura do banco de dados para springrest
CREATE DATABASE IF NOT EXISTS `springrest` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `springrest`;


-- Copiando estrutura para tabela springrest.tb_empresa
DROP TABLE IF EXISTS `tb_empresa`;
CREATE TABLE IF NOT EXISTS `tb_empresa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nomeApp` varchar(60) DEFAULT NULL,
  `nome` varchar(60) DEFAULT NULL,
  `cnpj` varchar(30) DEFAULT NULL,
  `idioma` varchar(10) DEFAULT NULL,
  `timezone` varchar(30) DEFAULT NULL,
  `classificacao` varchar(45) DEFAULT NULL,
  `isDeficiente` tinyint(1) DEFAULT NULL,
  `numeroLicensas` int(11) DEFAULT NULL,
  `imagem` varchar(100) DEFAULT NULL,
  `nomeAdministrador` varchar(60) DEFAULT NULL,
  `emailAdministrador` varchar(45) DEFAULT NULL,
  `passwordAdministrador` varchar(45) DEFAULT NULL,
  `isAtivo` tinyint(1) DEFAULT NULL,
  `planoId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_TB_Plano_idx` (`planoId`),
  CONSTRAINT `FK_TB_Plano` FOREIGN KEY (`planoId`) REFERENCES `tb_plano` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela springrest.tb_empresa: ~0 rows (aproximadamente)
DELETE FROM `tb_empresa`;
/*!40000 ALTER TABLE `tb_empresa` DISABLE KEYS */;
INSERT INTO `tb_empresa` (`id`, `nomeApp`, `nome`, `cnpj`, `idioma`, `timezone`, `classificacao`, `isDeficiente`, `numeroLicensas`, `imagem`, `nomeAdministrador`, `emailAdministrador`, `passwordAdministrador`, `isAtivo`, `planoId`) VALUES
	(20, 'EmpresaApp', 'EmpresaNome', '13256984', 'Portugues', 'pt/BR', 'Livre', 0, 10, 'C:/Users/lprates/Desktop/projeto/upload/linux.jpg', 'Leandro Prates', 'lprates2@peopleway.com.br', '123456', 1, 1),
	(21, 'EmpresaApp2', 'EmpresaNome2', '13256984', 'Portugues', 'pt/BR', 'Livre', 0, 10, 'C:/Desktop/imagem.png', 'Leandro Prates', 'lprates@peopleway.com.br', '123456', 1, 1),
	(23, 'EmpresaApp2', 'EmpresaNome2', '13256984', 'Portugues', 'pt/BR', 'Livre', 0, 10, 'C:/Users/lprates/Desktop/projeto/upload/linux.jpg', 'Leandro Prates', 'lprates@peopleway.com.br', '123456', 1, NULL),
	(25, 'EmpresaApp7', 'EmpresaNome7', '13256984', 'Portugues', 'pt/BR', 'Livre', 0, 10, 'C:/Desktop/imagem.png', 'Leandro Prates', 'lprates7@springrest.com.br', '123456', 0, NULL),
	(26, 'EmpresaApp7', 'EmpresaNome7', '13256984', 'Portugues', 'pt/BR', 'Livre', 0, 10, 'C:/Desktop/imagem.png', 'Leandro Prates', 'lprates7@springrest.com.br', '123456', 0, NULL);
/*!40000 ALTER TABLE `tb_empresa` ENABLE KEYS */;


-- Copiando estrutura para tabela springrest.tb_perfil
DROP TABLE IF EXISTS `tb_perfil`;
CREATE TABLE IF NOT EXISTS `tb_perfil` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela springrest.tb_perfil: ~2 rows (aproximadamente)
DELETE FROM `tb_perfil`;
/*!40000 ALTER TABLE `tb_perfil` DISABLE KEYS */;
INSERT INTO `tb_perfil` (`id`, `nome`) VALUES
	(1, 'ROLE_ADMIN'),
	(2, 'ROLE_USER');
/*!40000 ALTER TABLE `tb_perfil` ENABLE KEYS */;


-- Copiando estrutura para tabela springrest.tb_usuario
DROP TABLE IF EXISTS `tb_usuario`;
CREATE TABLE IF NOT EXISTS `tb_usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(45) NOT NULL,
  `qtdAtendimentosSimultaneos` int(11) DEFAULT NULL,
  `isAtivo` tinyint(4) NOT NULL DEFAULT '1',
  `perfilId` int(11) DEFAULT NULL,
  `empresaId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_EmailPassword` (`email`,`password`),
  KEY `FK_TB_Perfil_idx` (`perfilId`),
  KEY `FK_TB_Empresa_idx` (`empresaId`),
  CONSTRAINT `FK_TB_Empresa` FOREIGN KEY (`empresaId`) REFERENCES `tb_empresa` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_TB_Perfil` FOREIGN KEY (`perfilId`) REFERENCES `tb_perfil` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela springrest.tb_usuario: ~2 rows (aproximadamente)
DELETE FROM `tb_usuario`;
/*!40000 ALTER TABLE `tb_usuario` DISABLE KEYS */;
INSERT INTO `tb_usuario` (`id`, `nome`, `email`, `password`, `qtdAtendimentosSimultaneos`, `isAtivo`, `perfilId`, `empresaId`) VALUES
	(1, 'Leandro', 'lprates', 'lprates', 5, 1, 1, 20),
	(13, 'Leandro Prates', 'lprates3', 'lprates', 26, 1, 2, 20);
/*!40000 ALTER TABLE `tb_usuario` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
