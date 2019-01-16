FROM mysql:5.6

# 確認用
# ENV TZ=Asia/Tokyo
# RUN echo $TZ | tee /etc/timezone && dpkg-reconfigure --frontend noninteractive tzdata

ADD my.cnf /etc/mysql/conf.d/my.cnf
ADD dbinit.sql /docker-entrypoint-initdb.d/

RUN chmod 644 /etc/mysql/conf.d/my.cnf /docker-entrypoint-initdb.d/*