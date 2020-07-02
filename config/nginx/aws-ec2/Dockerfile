FROM nginx:1.19-alpine
USER root
COPY default.conf /etc/nginx/conf.d/
RUN apt-get install software-properties-common
RUN add-apt-repository universe
RUN add-apt-repository ppa:certbot/certbot
RUN apt-get update
RUN apt-get install python-certbot-nginx
RUN systemctl reload nginx
RUN ufw allow 'Nginx Full'
RUN ufw delete allow 'Nginx HTTP'
RUN certbot --nginx -m vladquinn2010@gmail.com -d oauth.xfarm.xyz -d properties.xfarm.xyz