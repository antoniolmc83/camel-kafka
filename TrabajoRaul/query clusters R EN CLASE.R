install.packages("cluster")
install.packages("foreign")
library(cluster)

#########################################################
#  K-Medias                                             #
#########################################################

#-------------------------------------------------------#
# Importadores                                     #
#-------------------------------------------------------#
library(foreign)
#distritos=read.spss(file.choose(),
#                    use.value.labels=TRUE, max.value.labels=Inf, to.data.frame=TRUE)

data <-read.table(file.choose(),header=T,sep=";", dec = ".") 
head(data)

colnames(data) <- tolower(colnames(data))
nombres=data[,1]
data=data[,-1]
rownames(data)=nombres
head(data)

set.seed(100)


res<-kmeans(scale(data),4)
res

#-----------------------------------------#
# Perfilado y caracterización de clusters #
#-----------------------------------------#

# Adicionar los cluster a la base de datos
data.new<-cbind(data,res$cluster)
colnames(data.new)<-c(colnames(data.new[,-length(data.new)]), "cluster.km")
head(data.new)


write.table(data.new,file= "data_kmeans.csv", dec=".", sep=";")

# Tabla de medias
med<-aggregate(x = data.new[,1:9],by = list(data.new$cluster.km),FUN = mean)
med


# Describir variables
par(mfrow=c(2,9))
for (i in 1:length(data.new[,1:9])) {
boxplot(data.new[,i]~data.new$cluster.km, main=names(data.new[i]), type="l")
}
par(mfrow=c(1,1))


base=read.csv(file.choose(),sep = ";")
head(base)
colnames(base) <- tolower(colnames(base))
nombres=base[,1]
distritos=base[,-1]
rownames(base)=nombres
res<-kmeans(scale(base[
  ,-1]),2)
res
