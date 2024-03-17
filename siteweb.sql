-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 27 avr. 2023 à 07:48
-- Version du serveur : 8.0.31
-- Version de PHP : 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `siteweb`
--

-- --------------------------------------------------------

--
-- Structure de la table `comment`
--

DROP TABLE IF EXISTS `comment`;
CREATE TABLE IF NOT EXISTS `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_user` int NOT NULL,
  `id_movie` int NOT NULL,
  `comment` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `comment`
--

INSERT INTO `comment` (`id`, `id_user`, `id_movie`, `comment`) VALUES
(1, 12, 4, 'super film d\'action dommage que ce soit le dernier de la saga.'),
(2, 12, 7, 'ce film est super il me rappelle vraiment mon enfance !!'),
(6, 4, 4, 'l\'un des meilleures fast And furious que j\'ai vu de ma vie dommage que ce soit le dérnier vin diesel va nous manquer'),
(7, 8, 4, 'un film qui a marqué mon enfance il restera a jamais dans mes souvenirs !!'),
(8, 4, 1, 'trés beau film comme d\'hab'),
(9, 12, 1, 'magnifique!!!'),
(10, 7, 4, 'Super film, hate de voir la partie 2'),
(23, 16, 52, 'ce film est super!!'),
(20, 10, 4, 'teydfrgdht'),
(21, 10, 4, 'teydfrgdhhhgdght'),
(22, 10, 4, 'teydfrgdhhgjfyjfyfnhhgdght'),
(17, 13, 7, 'excellent film mais je mets 2 parceque c\'est pas Batman'),
(18, 10, 4, 'test test'),
(19, 10, 4, 'test tyerrrsdtest');

-- --------------------------------------------------------

--
-- Structure de la table `movie`
--

DROP TABLE IF EXISTS `movie`;
CREATE TABLE IF NOT EXISTS `movie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titre` varchar(50) NOT NULL,
  `year` year NOT NULL,
  `genre` varchar(50) NOT NULL,
  `id_realisateur` int NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `url_image` text NOT NULL,
  `id_realisateur_a_realise` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `movie_realisateur_FK` (`id_realisateur_a_realise`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `movie`
--

INSERT INTO `movie` (`id`, `titre`, `year`, `genre`, `id_realisateur`, `description`, `url_image`, `id_realisateur_a_realise`) VALUES
(1, 'Black Adam', 2022, 'action', 1, 'Dans l\'ancien Kahndaq, Teth Adam a reçu les pouvoirs tout-puissants des dieux. Après avoir utilisé ces pouvoirs pour se venger, il a été emprisonné, devenant Black Adam. Près de 5 000 ans se sont écoulés et Black Adam est passé de l\'homme au mythe puis à la légende.', 'https://fr.web.img4.acsta.net/c_310_420/pictures/22/10/04/14/00/5857542.jpg', 1),
(4, 'Fast & Furious X', 2023, 'Action', 3, 'Fast and Furious 10 ou Rapides 10 au Québec, est un film d\'action franco-américain réalisé par Louis Leterrier et dont la sortie est prévue en 2023. Il s\'agit du 11ᵉ et dernier long métrage découpé en deux parties de la franchise Fast and Furious et la suite du film Fast and Furious 9 sorti en 2021.', 'https://fr.web.img5.acsta.net/pictures/23/03/14/16/24/2397758.jpg', 3),
(5, 'Insaisissables', 2013, 'Thriller', 3, 'Les Quatre Cavaliers, un groupe de brillants magiciens et illusionnistes, vient de donner deux spectacles de magie époustouflants : le premier en braquant une banque sur un autre continent, le deuxième en transférant la fortune d\'un banquier véreux sur les comptes en banque du public. Deux agents spéciaux du FBI et d\'Interpol sont déterminés à les arrêter avant qu\'ils ne mettent à exécution leur promesse de réaliser des braquages encore plus audacieux.', 'https://fr.web.img4.acsta.net/pictures/210/082/21008214_20130524171741313.jpg', 3),
(7, 'SUPER MARIO BROS', 2023, 'Comédie', 4, 'Un plombier nommé Mario parcourt un labyrinthe souterrain avec son frère, Luigi, essayant de sauver une princesse capturée. Adaptation cinématographique du célèbre jeu vidéo.', 'https://fr.web.img5.acsta.net/pictures/23/03/20/14/57/4979368.jpg', 4),
(8, 'Le Transporteur 2', 2005, 'Action', 3, 'Franck Martin vit désormais à Miami. Il a accepté de dépanner un ami, parti en vacances, en assurant la sécurité d\'un petit garçon, Jack Billings, durant son trajet quotidien de son domicile à l\'école, en lui servant de chauffeur. Mais Jack n\'est autre que le fils d\'un des plus hauts responsables de la lutte antidrogue et Franck se retrouve impliqué, malgré lui, dans un incroyable complot.', 'https://fr.web.img3.acsta.net/medias/nmedia/18/35/83/18/18435159.jpg', 3),
(9, 'The Passenger', 2018, 'Thriller', 1, 'Comme tous les jours après son travail, Michael MacCauley prend le train de banlieue qui le ramène chez lui. Mais cette fois, son trajet prend une tout autre tournure. Après avoir reçu l\'appel d\'un mystérieux inconnu, il doit identifier un passager caché dans le train, avant le dernier arrêt.', 'https://fr.web.img5.acsta.net/pictures/17/12/05/14/02/5796977.jpg', 1),
(10, 'Le Nom du fils', 2019, 'Comédie', 2, 'Tina et Fred, un couple de quinquas. La chronique d\'un petit bout de vie, rien de plus normal. Sauf leur obsession : ils semblent désespérément à la recherche d\'un prénom de garçon. Pour qui ? Pourquoi ? Un regard bienveillant sur un sujet de société.', 'https://medias.unifrance.org/medias/130/70/214658/format_page/media.png', 2),
(11, 'sage homme', 2023, 'Comédie', 2, 'Après avoir raté le concours d’entrée en médecine, Léopold intègre par défaut l’école des sage-femmes en cachant la vérité à son entourage. Alors qu’il s’engage sans conviction dans ce milieu exclusivement féminin, sa rencontre avec Nathalie, sage-femme d’expérience au caractère passionné, va changer son regard sur cet univers fascinant et bouleverser ses certitudes.', 'data:image/jpeg;base64,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', 2),
(12, 'Avatar 2', 2022, 'Science Fiction', 5, 'Jake Sully et Ney\'tiri ont formé une famille et font tout pour rester aussi soudés que possible. Ils sont cependant contraints de quitter leur foyer et d\'explorer les différentes régions encore mystérieuses de Pandora. Lorsqu\'une ancienne menace refait surface, Jake va devoir mener une guerre difficile contre les humains.', 'https://fr.web.img4.acsta.net/pictures/22/11/02/14/49/4565071.jpg', 5),
(13, 'Jungle Cruise', 2021, 'Aventure', 1, 'Chercheuse intrépide, la doctoresse Lily Houghton quitte Londres pour explorer la jungle amazonienne à la recherche d\'un remède miraculeux. Pour descendre le fleuve, elle engage Frank Wolff, un capitaine roublard aussi douteux que son vieux rafiot délabré. Bien décidée à découvrir l\'arbre séculaire dont les extraordinaires pouvoirs de guérison pourraient changer l\'avenir de la médecine, Lily se lance dans une quête épique.', 'https://fr.web.img4.acsta.net/pictures/21/05/27/15/44/2102945.jpg', 1),
(14, 'Creed 3', 2023, 'Sport', 6, 'Dominant toujours le monde de la boxe, Adonis Creed prospère dans sa carrière et sa vie de famille. Lorsque Damian, ami d\'enfance et ancien prodige de la boxe, refait surface après avoir purgé une peine de prison, il a hâte de prouver qu\'il mérite sa chance dans l\'enceinte.', 'https://fr.web.img6.acsta.net/pictures/23/02/10/10/56/3228985.jpg', 6),
(15, 'Mascarade', 2022, 'Comédie', 7, 'Le séduisant Adrien était promis à une carrière de danseur, jusqu\'à un terrible accident de moto. Aujourd\'hui, il est entretenu par Martha, ancienne vedette du cinéma vivant sur la Côte d\'Azur. Tout va changer lorsqu\'Adrien fait la rencontre de la belle et jeune Margot. Cette dernière vit d\'escroqueries et de manipulations. Ils vont alors s\'associer pour monter une arnaque diabolique grâce à une mascarade sentimentale. Ils ciblent alors Simon, un agent immobilier.', 'https://fr.web.img2.acsta.net/pictures/22/07/21/12/22/2437096.jpg', 7),
(16, 'Ghosted', 2023, 'Action', 8, 'Lorsque Cole tombe follement amoureux de la mystérieuse Sadie, il est loin de se douter qu’elle est en réalité un agent secret. Alors que Cole est bien décidé à revoir Sadie, ils sont soudain entraînés dans une aventure pour sauver le monde.', 'https://fr.web.img5.acsta.net/c_310_420/pictures/23/03/07/10/22/0062410.jpg', 8),
(17, 'Athena', 2022, 'Drame', 9, 'Quelques heures après la mort tragique de leur plus jeune frère dans des circonstances inexpliquées, la vie de la famille restante est bouleversée. La fratrie se retrouve rapidement dans un chaos qu\'elle a du mal à surmonter.', 'https://fr.web.img5.acsta.net/pictures/22/09/02/11/45/0753062.jpg', 9),
(18, 'Fast & Furious : Hobbs & Shaw', 2019, 'Action', 10, 'Depuis le premier affrontement entre Hobbs, loyal agent des services de sécurité de la diplomatie américaine, et Shaw, ancien membre de l\'élite de l\'armée britannique, ils n\'ont cessé de s\'attaquer.', 'https://fr.web.img4.acsta.net/pictures/19/06/13/16/39/3685647.jpg', 10),
(19, 'Billionaire Boys Club', 2018, 'Drame', 11, 'Au milieu des années 1980, le jeune et ambitieux Joe Hunt, un entrepreneur dont la société Billionaire Boys Club est composée d\'héritiers fortunés de Beverly Hills, met en place une arnaque basée sur une chaine de Ponzi.', 'https://fr.web.img4.acsta.net/pictures/19/01/22/13/24/3173009.jpg', 11),
(20, 'L\'Incroyable Hulk', 2008, 'Science Fiction', 3, 'Tapi dans les bas-fonds de Sao Paulo, Bruce Banner tente désespérément de percer le secret de la maladie qui l\'afflige.', 'https://fr.web.img2.acsta.net/medias/nmedia/18/65/69/21/18949753.jpg', 3),
(21, 'Loin du périph', 2022, 'Comédie', 3, 'Dix ans après avoir fait équipe, Ousmane et François, deux flics que tout oppose, reforment à contrecœur leur tandem de choc. Pas franchement ravis de se retrouver, ils mettent le cap sur une petite ville des Alpes pour enquêter sur un meurtre particulièrement sordide.', 'https://fr.web.img3.acsta.net/pictures/22/05/05/14/30/4993969.jpg', 3),
(22, 'Deadpool 2', 2018, 'Aventure', 10, 'L\'insolent mercenaire de Marvel est de retour. Deadpool devra réunir une équipe de mutants dissidents pour protéger un garçon aux habilités extraordinaires de Cable, un super-soldat cyborg malveillant voyageant dans le temps. Bottage de fesses en perspective, car pour faire le bien, il faut parfois savoir faire le mal..', 'https://fr.web.img4.acsta.net/pictures/18/04/06/16/26/2317955.jpg', 10),
(23, 'Uncharted', 2022, 'Aventure', 12, 'Le chasseur de trésors Victor Sully Sullivan recrute Nathan Drake pour l\'aider à récupérer une fortune vieille de 500 ans amassée par l\'explorateur Ferdinand Magellan.', 'https://fr.web.img6.acsta.net/pictures/22/01/18/10/13/5983633.jpg', 12),
(24, 'Venom', 2018, 'Science Fiction', 12, 'Eddie Brock est un journaliste d\'enquête très connu à San Francisco. Après avoir fait déraper une entrevue avec Carlton Drake, le fondateur de la puissante compagnie Life Foundation, il perd son emploi. Quelques mois plus tard, quand une employée de Drake le rejoint afin de lui faire part des dangereuses expériences qui sont menées dans les laboratoires de la Life Foundation, Eddie se rend sur place pour investiguer. Il entrera alors en contact avec un symbiote venu d\'une autre planète.', 'https://fr.web.img3.acsta.net/pictures/18/09/20/10/41/4338768.jpg', 12),
(25, 'Gangster Squad', 2013, 'Action', 12, 'Le parrain de la mafia, Mickey Cohen, dirige Los Angeles et obtient tout ce qu\'il veut grâce à la protection de ses hommes de mains, mais aussi de la police et des hommes politiques sous sa coupe. Seuls deux sergents du LAPD, John O\'Mara et Jerry Wooters, font tout pour réduire son emprise à néant.', 'https://fr.web.img6.acsta.net/medias/nmedia/18/92/13/72/20446220.jpg', 12),
(26, 'Les Misérables', 2019, 'Drame', 9, 'Stéphane, tout juste arrivé de Cherbourg, intègre la Brigade anti-criminalité de Montfermeil, dans le 93. Il va faire la rencontre de ses nouveaux coéquipiers, Chris et Gwada, deux \'Bacqueux\' d\'expérience. Il découvre rapidement les tensions entre les différents groupes du quartier. Alors qu\'ils se trouvent débordés lors d\'une interpellation, un drone filme leurs moindres faits et gestes.', 'https://fr.web.img6.acsta.net/pictures/20/03/02/13/58/2855355.jpg', 9),
(27, 'Le Bal de l\'Enfer', 2022, 'Horreur', 13, 'Après la mort de sa mère, Evie se retrouve sans famille. Elle décide de faire un test ADN et se découvre un cousin éloigné dont elle ne soupçonnait pas l\'existence. Après être entrée en contact avec lui, il l\'invite à un somptueux mariage dans la campagne anglaise afin qu\'elle rencontre sa nouvelle famille. D\'abord sous le charme du séduisant aristocrate qui accueille les festivités, elle se retrouve rapidement plongée dans une lutte infernale pour sa survie.', 'https://fr.web.img2.acsta.net/pictures/22/07/04/08/08/2148141.jpg', 13),
(28, 'Nope', 2022, 'Horreur', 14, 'Les habitants d\'une vallée perdue du fin fond de la Californie sont témoins d\'une découverte terrifiante à caractère surnaturel qui affecte humains et animaux. Les gérants d\'un ranch de chevaux tentent de comprendre ce phénomène mystérieux alors que le propriétaire d\'un parc à thème tente d\'en tirer profit.', 'https://fr.web.img6.acsta.net/pictures/22/03/07/12/13/4369919.jpg', 14),
(29, 'Get Out', 2017, 'Horreur', 14, 'Maintenant que Chris et sa copine Rose vont rencontrer leurs parents respectifs, elle l\'invite dans la résidence secondaire de sa famille pour un week-end. D\'abord Chris comprend que le comportement un peu étrange de la famille de Rose est lié au fait qu\'il est noir et qu\'elle est blanche. Cependant, il découvre que la vérité est bien plus dérangeante.', 'https://fr.web.img4.acsta.net/pictures/17/03/16/13/25/487327.jpg', 14),
(30, 'Us', 2019, 'Horreur', 14, 'Les vacances s\'annoncent reposantes pour Adelaide et Gabe Wilson et leurs deux enfants. À peine les valises posées dans la maison au bord d\'un lac qu\'Adelaide, cédant à la pression, accepte à contrecoeur d\'aller passer la journée à la plage de Santa Cruz.', 'https://fr.web.img3.acsta.net/pictures/19/03/14/10/51/3421837.jpg', 14),
(31, 'Pearl', 2022, 'Horreur', 15, 'Pearl habite dans une pension de famille en pleine campagne. La jeune femme se sent piégée et isolée dans cette ferme familiale où elle doit s\'occuper de son père malade et comateux tout en supportant une mère autoritaire. Pearl rêve d\'une vie glamour comme dans les films qu\'elle regarde. Une série d\'événements va doucement la faire sombrer.', 'https://fr.web.img4.acsta.net/pictures/22/07/25/20/34/1901466.jpg', 15),
(32, 'X', 2022, 'Horreur', 15, 'En 1979, un groupe de jeunes cinéastes entreprend de tourner un film pour adultes dans la campagne du Texas, mais lorsque leurs hôtes les surprennent en flagrant délit, les acteurs se retrouvent rapidement dans une lutte désespérée pour leur vie.', 'https://fr.web.img4.acsta.net/pictures/22/10/04/15/25/4773818.jpg', 15),
(33, 'The House of the Devil', 2009, 'Horreur', 15, 'The House of the Devil est un film d\'horreur américain écrit et réalisé par Ti West sorti en 2009, avec Jocelin Donahue, Tom Noonan et Mary Woronov. Le film combine les éléments des slashers des années 1980, des films de maison hantée et aussi un peu de satanisme.', 'https://fr.web.img6.acsta.net/c_310_420/medias/nmedia/18/72/32/64/19160065.jpg', 15),
(34, 'The Batman', 2022, 'Aventure', 16, 'Dans sa deuxième année de lutte contre le crime, le milliardaire et justicier masqué Batman explore la corruption qui sévit à Gotham et notamment comment elle pourrait être liée à sa propre famille, les Wayne, à qui il doit toute sa fortune. En parallèle, il enquête sur les meurtres d\'un tueur en série qui se fait connaître sous le nom de Sphinx et sème des énigmes cruelles sur son passage.', 'https://fr.web.img5.acsta.net/pictures/22/02/16/17/42/3125788.jpg', 16),
(35, 'La Planète des singes : Suprématie', 2017, 'Science Fiction', 16, 'Les singes menés par César cherchent de la paix après la mort de Koba, mais lorsqu\'un méchant colonel décide de perpétrer un massacre auprès des primates, César sait qu\'il faut se battre contre cet homme et son armée. Cette bataille déterminera le destin de la planète.', 'https://fr.web.img4.acsta.net/pictures/17/04/28/09/41/203328.jpg', 16),
(36, 'Laisse-moi entrer', 2010, 'Horreur', 16, 'Malmené à l\'école, négligé chez lui et incroyablement seul, Owen, 12 ans, passe ses journées à fomenter des vengeances contre ses persécuteurs et passe ses nuits à espionner ses colocataires. Son unique amie est Abby, une étrange fille qui sort seulement la nuit. Les deux sont très unis.', 'https://fr.web.img6.acsta.net/medias/nmedia/18/78/89/82/19502725.jpg', 16),
(37, 'X-Men: Days of Future Past', 2014, 'Science Fiction', 17, 'Dans un futur proche, les mutants et les humains ont été décimés par les Sentinelles, des robots biologiques capables de copier les pouvoirs des mutants. Seuls quelques mutants, dont Kitty Pride, Iceman, Storm et Bishop, résistent encore.', 'https://fr.web.img6.acsta.net/pictures/14/03/24/14/35/575642.jpg', 17),
(38, 'X-Men', 2000, 'Science Fiction', 17, 'Dans un camp de concentration en 1944. Séparé par la force de ses parents, le jeune Erik Magnus Lehnsherr se découvre d\'étranges pouvoirs sous le coup de la colère: il peut contrôler les métaux. C\'est un mutant.', 'https://fr.web.img3.acsta.net/medias/nmedia/18/66/09/04/19089552.jpg', 17),
(39, 'X-Men: Apocalypse', 2016, 'Science Fiction', 17, 'Depuis les origines de la civilisation, Apocalypse, le tout premier mutant, a absorbé de nombreux pouvoirs, devenant à la fois immortel et invincible, adoré comme un dieu.', 'https://fr.web.img6.acsta.net/pictures/16/04/13/17/27/399447.jpg', 17),
(40, 'Superman Returns', 2006, 'Aventure', 17, 'Alors qu\'un ennemi de longue date, Lex Luthor, tente de l\'affaiblir en lui dérobant tous ses superpouvoirs, Superman doit faire face à un problème d\'un autre genre : Lois Lane, la femme qu\'il aime, est partie mener une nouvelle vie loin de lui. L\'a-t-elle vraiment oublié ? Le retour de Superman sera donc ponctué de nombreux défis puisqu\'il devra se rapprocher de son amour tout en retrouvant sa place au sein d\'une société qui semble ne plus avoir besoin de lui.', 'https://fr.web.img4.acsta.net/medias/nmedia/18/35/78/09/18648048.jpg', 17),
(41, 'Zack Snyder\'s Justice League', 2021, 'Action', 18, 'Bruce Wayne veut faire en sorte que le sacrifice ultime de Superman ne soit pas vain. Avec l\'aide de Diana Prince, il met en place un plan pour recruter une équipe de métahumains afin de protéger le monde d\'une menace apocalyptique imminente. Or, la tâche s\'avère plus difficile que Bruce ne l\'imaginait. En effet, chacune des recrues doit faire face aux démons de son passé et doit les surpasser pour se rassembler et former une ligue de héros sans précédent.', 'https://fr.web.img6.acsta.net/pictures/21/04/07/10/59/5550346.jpg', 18),
(42, 'Man of Steel', 2013, 'Science Fiction', 18, 'Lorsque Jor-El comprend que sa planète, Krypton, est au bord de l\'annihilation, il décide d\'envoyer son fils unique, Kal-El, sur la planète Terre dans le but de sauver sa race.', 'https://fr.web.img3.acsta.net/pictures/210/081/21008110_20130524125237634.jpg', 18),
(43, 'Batman v Superman : L\'Aube de la justice', 2016, 'Aventure', 18, 'Craignant les débordements d\'un superhéros aux pouvoirs infinis, le justicier de Gotham City, lui-même doté d\'une détermination et d\'une force de frappe redoutables, affronte le sauveur des temps modernes adulé de Metropolis sous le regard du monde entier qui se demande quel type de héros lui convient le mieux.', 'https://fr.web.img2.acsta.net/pictures/16/02/03/11/17/130929.jpg', 18),
(44, 'Army of the Dead', 2021, 'Action', 18, 'À la suite d\'une attaque de zombies à Las Vegas, la ville a été mise en quarantaine, mais le patron d\'un casino, Bly Tanaka, y voit une occasion en or et envoie une équipe d\'experts dans la zone isolée pour réaliser le plus grand braquage de l\'Histoire et voler 200 millions de dollars.', 'https://fr.web.img5.acsta.net/pictures/21/04/15/12/40/4834460.jpg', 18),
(45, 'Aquaman', 2018, 'Aventure', 18, 'Jadis l\'accueil de la civilisation ancienne la plus avancée de la Terre, Atlantide est maintenant un royaume sous-marin gouverné par le Roi Orm. Aidé par une armée, Orm désire conquérir les autres civilisations océaniques -- et ensuite le monde terrain.', 'https://fr.web.img3.acsta.net/pictures/19/02/01/21/02/1004291.jpg', 18),
(46, 'Army of Thieves', 2021, 'Thriller', 18, 'Ludwig Dieter, banquier discret, mène une vie morne dans une petite ville. Lassé par les sempiternelles routines, Ludwig rêve de pouvoir s\'échapper de son quotidien monotone. Lorsqu\'il rencontre un jour la mystérieuse Gwendoline, c\'est le début d\'une nouvelle vie. Ludwig est chargé de cambrioler des coffres-forts dans toute l\'Europe avec une équipe internationale de criminels.', 'https://fr.web.img6.acsta.net/pictures/21/08/02/13/59/5159648.jpg', 18),
(47, 'Non-Stop', 2014, 'Thriller', 1, 'Responsable de la sécurité des passagers, l\'agent fédéral Bill Marks voyage beaucoup bien qu\'il déteste l\'avion. Mais c\'est son métier et aujourd\'hui il est bien obligé d\'embarquer sur ce vol pour Londres. Alors qu\'il survole l\'Atlantique, il reçoit un SMS provenant d\'un autre passager qui lui annonce que quelqu\'un va mourir, sauf si l\'on effectue un virement de 150 millions de dollars sur un compte en Suisse.', 'https://fr.web.img2.acsta.net/pictures/14/01/09/14/47/339936.jpg', 1),
(48, 'Sans Identité', 2011, 'Thriller', 1, 'Alors qu\'il se trouve à Berlin pour donner une conférence, le docteur Martin Harris est victime d\'un grave accident de taxi. Il tombe dans le coma et se réveille plusieurs jours plus tard, à l\'hôpital. Sa vie a alors basculé. Personne, pas même sa propre femme, Elizabeth, ne le reconnaît. Il découvre bientôt qu\'un homme a usurpé son identité et qu\'il cherche à le tuer. Les autorités refusent de l\'écouter et Martin se retrouve seul, exténué et en cavale.', 'https://fr.web.img4.acsta.net/medias/nmedia/18/82/09/96/19621657.jpg', 1),
(49, 'Black Panther: Wakanda Forever', 2022, 'Action', 6, 'La reine Ramonda, Shuri, M\'Baku, Okoye et la Dora Milaje se battent pour protéger leur nation des puissances mondiales intervenantes à la suite de la mort du roi T\'Challa.', 'https://fr.web.img2.acsta.net/pictures/22/10/17/10/28/5232325.jpg', 6),
(50, 'Creed : L\'Héritage de Rocky Balboa', 2015, 'Drame', 6, 'Après quelques victoires faciles sur le ring de boxe, Adonis, fils du grand champion Apollo Creed, tente de se tailler une place parmi les professionnels. Il quitte Los Angeles et se rend à Philadelphie pour rencontrer Rocky Balboa, vieux rival et ami de son père.', 'https://fr.web.img6.acsta.net/pictures/15/11/09/16/24/355502.jpg', 6),
(51, 'Sans aucun remords', 2021, 'Action', 6, 'Tandis qu\'il cherche à obtenir justice pour le meurtre de sa femme enceinte, John Kelly découvre une conspiration internationale, qui menace d\'entraîner les États-Unis et la Russie dans une guerre totale. L\'ancien marine des forces spéciales américaines va combattre ses ennemis sans aucun remords et révéler les puissants derrière le complot.', 'https://fr.web.img6.acsta.net/pictures/21/04/07/10/10/4273666.jpg', 6),
(52, 'Les 4 Fantastiques', 2015, 'Science Fiction', 6, 'Quatre jeunes scientifiques se téléportent dans un univers parallèle dangereux qui fera subir à leurs corps des transformations choquantes. Leurs vies étant transformées à jamais, ils devront apprendre à maîtriser leurs nouvelles capacités et à travailler ensemble afin de sauver la Terre d\'un ancien ami devenu ennemi.', 'https://fr.web.img5.acsta.net/pictures/15/07/10/12/04/290362.jpg', 6),
(53, ' My Spy', 2020, 'Action', 24, 'L\'agent JJ doit accomplir une mission de surveillance avec sa collègue dans un immeuble modeste. Il doit garder un oeil sur Kate, une femme dont l\'ex-mari criminel a été assassiné. Cependant, la mission est mise en péril lorsqu\'une jeune fille de 9 ans, Sophie, découvre leur planque et les détails de leur mission. La fillette menace de tout révéler à ses supérieurs à moins que JJ lui apprenne le métier d\'espion.', 'https://fr.web.img6.acsta.net/pictures/19/04/04/11/43/4811013.jpg', 24);

-- --------------------------------------------------------

--
-- Structure de la table `news`
--

DROP TABLE IF EXISTS `news`;
CREATE TABLE IF NOT EXISTS `news` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titre` text NOT NULL,
  `text` text NOT NULL,
  `id_user` int NOT NULL,
  `url_image` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `news`
--

INSERT INTO `news` (`id`, `titre`, `text`, `id_user`, `url_image`) VALUES
(1, 'La Vie pour de vrai : le nouveau film de Dany Boon est né au Club Med !', 'La veille de la sortie en salles de « La Vie pour de vrai », l\'équipe du film s\'est rendue à une ultime avant-première parisienne, au cinéma Pathé Wepler. Mardi 18 avril, le trio à l\'affiche, Dany Boon, Kad Merad et Charlotte Gainsbourg, s\'est à nouveau réuni pour clôturer une longue tournée de promotion partout en France.  Pour l\'occasion, Dany Boon était entouré de sa famille. Il est arrivé au bras de sa compagne, la comédienne Laurence Arné, avec qui il file le parfait amour depuis maintenant cinq ans. Sa fille cadette Sarah, 13 ans, a également pris la pose en leur compagnie. Et pour cause, l\'adolescente a décroché un petit rôle dans la nouvelle comédie de son père. Même s\'il a fallu du temps au comédien pour la convaincre, cette dernière a finalement accepté de relever le challenge.', 4, 'https://culture-tops.ouest-france.fr/img/la-vie-pour-de-vrai-6779.jpg'),
(2, 'L\'un des meilleurs films catastrophe aura une suite avec un acteur de Top Gun', 'Twisters\", la suite du film catastrophe culte des années 90 recrute du côté de \"Top Gun : Maverick\" et \"Transformers 7\". Le film sur les chasseurs de tornades est prévu dans nos salles en juillet 2024.', 4, 'https://imgsrc.cineserie.com/2016/12/669411.jpg?ver=1'),
(3, 'Ça cartonne sur Netflix : Pierre Niney en mode aventurier dans un film romanesque et épique', 'Vous êtes fans de Pierre Niney ? Vous avez envie de le voir évoluer dans un film d\'aventure au souffle épique ? Alors rendez-vous sur Netflix pour découvrir le romanesque La Promesse de l\'aube, long métrage qui rencontre actuellement un joli succès sur la plateforme !', 4, 'https://fr.web.img6.acsta.net/c_640_360/img/05/b7/05b79417b1c24fa4e6652b74410fc7d1.jpg'),
(4, 'Evil Dead : Sam Raimi détestait le titre de son film', 'Censuré dans de nombreux pays (au moins huit), ultra gore, irrigué par un humour potache de sale gosse, des effets spéciaux artisanaux qui transpirent la débrouille et l\'amour pour le matériau d\'origine, Evil Dead est devenu depuis sa sortie un étendard du genre. Véritable film séminal signé par Sam Raimi, qui enfantera d\'ailleurs des suites, série et reboot. La dernière itération en date, Evil Dead Rise, est d\'ailleurs en salle depuis le 19 avril.', 4, 'https://fr.web.img2.acsta.net/medias/nmedia/18/93/97/92/20539128.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `rating`
--

DROP TABLE IF EXISTS `rating`;
CREATE TABLE IF NOT EXISTS `rating` (
  `id` int NOT NULL AUTO_INCREMENT,
  `value` int NOT NULL,
  `movie_id` int NOT NULL,
  `id_user` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `rating`
--

INSERT INTO `rating` (`id`, `value`, `movie_id`, `id_user`) VALUES
(1, 3, 4, 6),
(2, 5, 4, 4),
(3, 5, 7, 4),
(4, 5, 11, 4),
(5, 5, 10, 4),
(6, 4, 1, 4),
(7, 4, 8, 12),
(8, 5, 4, 12),
(9, 5, 1, 12),
(10, 4, 7, 12),
(11, 5, 11, 12),
(12, 5, 10, 12),
(13, 5, 15, 12),
(14, 3, 14, 12),
(15, 2, 5, 12),
(16, 2, 7, 13),
(17, 5, 52, 16),
(18, 3, 52, 4),
(19, 3, 52, 4);

-- --------------------------------------------------------

--
-- Structure de la table `realisateur`
--

DROP TABLE IF EXISTS `realisateur`;
CREATE TABLE IF NOT EXISTS `realisateur` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `birth_year` year NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `realisateur`
--

INSERT INTO `realisateur` (`id`, `name`, `birth_year`) VALUES
(1, 'Jaume Collet-Serra\r\n', 1974),
(2, 'Jennifer Devoldere', 1974),
(3, 'Louis Leterrier', 1973),
(4, ' Aaron Horvath', 1980),
(5, 'James Cameron', 1954),
(6, 'Michael B. Jordan', 1987),
(7, 'Nicolas Bedos', 1979),
(8, 'Dexter Fletcher', 1966),
(9, 'Romain Gavras', 1981),
(10, 'David Leitch', 1975),
(11, 'James Cox', 1983),
(12, 'Ruben Fleischer', 1974),
(13, 'Jessica M. Thompson', 1985),
(14, 'Jordan Peele', 1979),
(15, 'Ti West', 1980),
(16, 'Matt Reeves', 1966),
(17, 'Bryan Singer', 1965),
(18, 'Zack Snyder', 1966),
(19, 'John McTiernan', 1951),
(20, 'Michael Bay', 1965),
(21, 'Luc Besson', 1959),
(22, 'Bruce Willis', 1955),
(23, 'Gerard Butler', 1969),
(24, 'Peter Segal', 1978);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role` int NOT NULL DEFAULT '0',
  `pseudo` varchar(25) NOT NULL,
  `password` varchar(70) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `role`, `pseudo`, `password`, `email`) VALUES
(4, 1, 'idir1', '$2y$10$W5QyInbwzW28uSjlfF50B.6bnVdlxXxu5UEjsLKy4zBzey3LPtWvu', 'idir1@gmail.com'),
(5, 1, 'idir2', '$2y$10$WYhwolFvGuviN/o41JeF3.qEDypmVLnhuE2okJT.o/z1rmhpPcuf2', 'idir2@gmail.com'),
(6, 0, 'idir3', '$2y$10$/ImFpUB91B921rwfP/IaKeJj.LNvqA.9bdw68y3sIoXb0YC9a.brC', 'idir3@gmail.com'),
(7, 0, 'idir4', '$2y$10$9awLpcfmrc16E/ZUz3G0VeXU9cGdxulmNEYBiDCk6iPhlRoMg.XAW', 'idir4@gmail.com'),
(8, 0, 'idir5', '$2y$10$/piM9QV2m0lBqnDzwx1nSuXD3DePbrfwz94GT3Se78O4zCr2kVPba', 'idir5@gmail.com'),
(9, 0, 'idir6', '$2y$10$Nyw1Zl52Usr2IVvei0T8ZecBESRQElAYSEHWx4.o27mq4mUhzQ/m6', 'idir6@gmail.com'),
(10, 1, 'idir12', '$2y$10$ma45qfvShCJ9V0hIkH/SjuMUXhg2JzNLETbbuUT6LYWJu13sB1J4K', 'idir12@gmail.com'),
(11, 1, 'chanez', '$2y$10$SNCFhhxUUK416BlYu4k/ve3tk7kWr8uc8viTZy53RiiE8/bySo1Ra', 'chanez@gmail.com'),
(12, 1, 'mehdi', '$2y$10$/4gxBv6yYdmsHY9a9G4FKezP5SANF.bMUY8Ua4mX71S5ExQzYXw9m', 'mehdi@gmail.com'),
(13, 0, 'batman', '$2y$10$vfJdVq9XMlPZGrgkknfQTOiQvmGGuCWl8pWKPeKTN20VNZHImwMAq', 'batman@batman.batman'),
(16, 0, 'demo', '$2y$10$rQgriaw01Vs2PSx6mjGkC.dnDJHqd82qIJv7/LGQCt6JNgmGbcoyK', 'demo@gmail.com');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `movie`
--
ALTER TABLE `movie`
  ADD CONSTRAINT `movie_realisateur_FK` FOREIGN KEY (`id_realisateur_a_realise`) REFERENCES `realisateur` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
