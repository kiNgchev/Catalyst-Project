FROM redis:6-alpine

ADD init.sh /
RUN ["chmod", "+x", "/init.sh"]
ENTRYPOINT ["/init.sh"]