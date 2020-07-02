RUN docker run --name nginx -p 80:80 -p 443:443 -d nginx:1.19
RUN docker cp default.conf nginx:/etc/nginx/conf.d/default.conf
RUN docker exec --privileged -it nginx bash
RUN apt-get update
RUN apt-get install software-properties-common -y
RUN add-apt-repository universe
RUN apt-get install certbot
RUN apt-get install python-certbot-nginx -y
RUN apt-get update
RUN certbot --nginx --email vladquinn2010@gmail.com --agree-tos --no-eff-email -d xfarm.xyz -d www.xfarm.xyz
