FROM ubuntu:18.04
USER root
RUN apt install nginx
RUN apt update
COPY default.conf /etc/nginx/conf.d/
RUN apt install software-properties-common -y
RUN add-apt-repository universe
RUN add-apt-repository ppa:certbot/certbot
RUN apt update
RUN apt install python-certbot-nginx -y
RUN systemctl restart nginx
RUN ufw allow 'Nginx Full'
RUN ufw delete allow 'Nginx HTTP'
RUN certbot --nginx -m vladquinn2010@gmail.com -d oauth.xfarm.xyz -d properties.xfarm.xyz