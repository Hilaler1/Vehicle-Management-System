# VehicleManagementSystem
*servisleri çalıştırdığımızda dbmizde 1 tane company 1 tane de companyadmin kayıtlı olarak geliyor.(email: admin@admin, password: 12345)(db adımız:vehicleDB)
*http://localhost:5050/swagger-ui/index.html#/ adresinden login olup token alarak başlayabilirsiniz.
*aynı adreste company/add metoduyla company ekleyebilirsiniz.
*artık aynı adreste bu companyId lere sahip standart userlar oluşturabilirsiniz.
*dilediğiniz kullanıcıya da companyadmin yetkisi verebilirsiniz.(/grantrole -> companyadmin rolüyle yapablirsiniz)
*yine dilediğiniz companyadmin rolünü tekrar standartuser a çekebilirsiniz (/revokerole -> companyadmin rolüyle yapablirsiniz)

*http://localhost:4040/swagger-ui/index.html#/ adresinden önce grup oluşturmalıyız.( /vehiclegroupadd metotduyla )
*burada oluşturduğumuz gruplar sub gruplar.örneğin avrupa ve asya yakasını ekleyip ardından /addparentgroup metoduyla İstanbulu ekleyip 
bu iki grubu istanbula bağlayabilirsiniz. Ya da Ankara, Eskişehir,Nevşehir gruplarını oluşturup /addparentgroup metoduyla bunları iç anadoluya bağlayabilirsiniz. 
Yine Ankara ve İstanbulu başka parentgroup a bağlayabilirsiniz. yani /vehiclegroupadd en küçük grupları temsil ediyor.
*Gruplar oluşturulduktan sonra hangi company için araç ekleyeceksek o companyadmin /vehiclecreate metoduyla şirkete ait araçları ekleyebilir.
Burada araç oluşturuken groupid si de veriliyor. bu yüzden grupları önce oluşturmamız gerekli.

*http://localhost:5050/swagger-ui/index.html#/ adresinden artık standart userlarımıza grup ya da araç yetkisi verebiliriz.
Burada company controllerından /givevehicleauthorize veya /givegroupauthorize metotlarıyla companyadmini olduğumuz şirketin standart userlarına yetki verebiliyoruz.
*standart userlar olarak bu yetkilendirildiğimiz araçları görebilmek için login olup /authenticatedvehicles veya /authenticatedgroup metodlarını kullanıyoruz.
