# web-lab-2
[report](https://github.com/kkkooolllyyyaaa/web-lab-2/blob/master/tsypandin.pdf)


# stack:

* JAVA 8
* JSP
* JavaScript
* CSS
* HTML5

technologies:
* maven
* wildfly 18
* lombok
* svg

# How it looks

![image](https://user-images.githubusercontent.com/72232007/137323212-44f0a549-5027-4965-85c9-d63ddfaafc5f.png)

![image](https://user-images.githubusercontent.com/72232007/137323263-371d8a9b-5a9e-4672-8db3-316133d82f7b.png)

# How to launch:
1. Download wildfly
2. Change jboss bind address in the standalone/configurations/standalone.xml from 127.0.0.1 to 0.0.0.0
4. Put your http and management ports in the standalone/configurations/standalone.xml
5. Launch by: JAVA=java18 wildfly-18.0.1.Final/bin/standalone.sh
6. Forward managament port by: ssh -p 2222 s------@se.ifmo.ru -Y -L{management port}:helios:{management port}
7. Forward client (http) port by: ssh -p 2222 s------@se.ifmo.ru -Y -L{http port}:helios:{http port}
8. Connect to 0.0.0.0:{managament port} and deploy your WAR.
9. Connect to 0.0.0.0:{http port}
10. That's it!
