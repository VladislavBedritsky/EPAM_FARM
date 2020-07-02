FROM nginx:1.19
USER root
RUN apt-get update
RUN apt-get install nginx -y
RUN apt-get update
COPY default.conf /etc/nginx/conf.d/
RUN apt-get install software-properties-common -y
RUN add-apt-repository universe
RUN add-apt-repository ppa:certbot/certbot
RUN apt-get update
RUN apt-get install python-certbot-nginx -y
RUN apt-get install ufw -y
RUN apt-get update
RUN ufw allow 'Nginx Full'
RUN ufw delete allow 'Nginx HTTP'
RUN certbot --nginx --email vladquinn2010@gmail.com --agree-tos --no-eff-email --force-renewal -d oauth.xfarm.xyz -d properties.xfarm.xyz
