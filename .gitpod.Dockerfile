FROM gitpod/workspace-full

# Install mongodb
RUN sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 9DA31620334BD75D9DCB49F368818C72E52529D4
RUN echo "deb [ arch=amd64 ] https://repo.mongodb.org/apt/ubuntu bionic/mongodb-org/4.0 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-4.0.list
RUN sudo apt-get update \
 && sudo apt-get install -y mongodb-org \
 && sudo apt-get clean \
 && sudo rm -rf /var/lib/apt/lists/*
RUN sudo mkdir -p /data/db \
 && sudo chown gitpod:gitpod -R /data/db

#Install mysql
RUN sudo apt --update \
&& wget -c https://repo.mysql.com//mysql-apt-config_0.8.13-1_all.deb \
&& sudo dpkg -i mysql-apt-config_0.8.13-1_all.deb \
&& sudo apt-get install -y mysql-server
