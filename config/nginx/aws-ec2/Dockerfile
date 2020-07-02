FROM xqdocker/ubuntu-nginx:0.1.4
USER root
COPY default.conf /etc/nginx/conf.d/
RUN apt-get update
RUN apt-get install software-properties-common -y
RUN add-apt-repository universe
RUN add-apt-repository ppa:certbot/certbot
RUN apt-get update
RUN apt-get install certbot -y
RUN systemctl reload nginx
RUN ufw allow 'Nginx Full'
RUN ufw delete allow 'Nginx HTTP'
RUN certbot --nginx -m vladquinn2010@gmail.com -d oauth.xfarm.xyz -d properties.xfarm.xyz