par(mar=c(2,2,2,2))

#Exercice 1
x <- seq(-5,5,by=0.1);
y <- dnorm(x);
plot(x,y,type='l',ylim=c(0,0.6),main='Lois normales')
z <- dnorm(x,m=0,sd=sqrt(2))
lines(x,z,col='blue')
a <- dnorm(x,m=0,sd=sqrt(1/2))
lines(x,a,col='red')

#Exercice 2
par(mfrow=c(1,3))
plot(x,y,type='l',ylim=c(0,0.6),main='N(0,1)')
plot(x,z,type='l',ylim=c(0,0.6),main='N(0,2)')
plot(x,a,type='l',ylim=c(0,0.6),main='N(0,1/2)')

#Exercice 3
tableau <- read.table(file= "C:/Users/charl/IFF1103/cats.txt", header = TRUE,
                      sep = " ")
class(tableau)#We get data.frame as expected
names(tableau)#We get Sex Bwt Hwt
dim(tableau) #We get 144

#Exercice 4
par(mfrow=c(1,2))
boxplot(tableau$Bwt,main='Poids total du chat')
boxplot(tableau$Hwt,main='Poids du coeur du chat')
summary(tableau)
#boxplot represents min, max, 1st quartile, 3rd quartile and the median
#As well as values that seems out of place
#So average cats have a weight between 8,95kg and 12,12kg
#And have hearts that have a weight between 2,3kg and 3,025kg

#Exercice 5
par(mfrow=c(1,1))
boxplot(tableau$Bwt~tableau$Sex,main='Poids total du chat',ylab='en Kg')
#Males are heavier than females

#Exercice 6
hist(tableau$Bwt,breaks=20,main='R�partition du poids des chats')
#Il semblerait que la valeur maximale inf�rieure en ordre de grandeur par rapport � l'�tendue totale
#soit la plus int�ressante pour �tudier l'histogramme

#Exercice 7
par(mfrow= c(1,1))
hist(tableau$Hwt[tableau$Sex=='M'],breaks=10,freq=FALSE,main='R�partition du poids des coeurs des m�les',xlab="Poids")
M <- mean(tableau$Hwt[tableau$Sex=='M'])
V <- var(tableau$Hwt[tableau$Sex=='M'])
x <- seq(2,4,by=0.01);
y <- dnorm(x, mean = M, sd= sqrt(V))
lines(x,y)
#The males seems to follow a normal law
par(mfrow= c(1,1))
hist(tableau$Hwt[tableau$Sex=='F'],breaks=10,freq=FALSE,main='R�partition du poids des coeurs des m�les',xlab="Poids")
M <- mean(tableau$Hwt[tableau$Sex=='F'])
V <- var(tableau$Hwt[tableau$Sex=='F'])
x <- seq(2,3,by=0.01);
y <- dnorm(x, mean = M, sd= sqrt(V))
lines(x,y)
#The females does not seem to follow a normal law

#Exercice 8
plot(tableau$Bwt[tableau$Sex=='M'],tableau$Hwt[tableau$Sex=='M'],ylim=c(0,7),col='blue')
points(tableau$Bwt[tableau$Sex=='F'],tableau$Hwt[tableau$Sex=='F'],ylim=c(0,7),col='red')
#The body weight seems to be proportionnal to the heart weight
cov(tableau$Bwt,tableau$Hwt)
cor(tableau$Bwt,tableau$Hwt)
#On remarque donc que les deux variables sont fortement li�es
#On remarque que les femelles ont des coeurs l�g�rement plus petits que ceux des m�les

#Exercice 9
eval <- read.table(file= "C:/Users/charl/IFF1103/evaluation.txt", header = TRUE,
                      sep = ";",row.names = 1)
names(eval)
dim(eval)
#Il y a dans ce tableau les statistiques pour 50 �tats
plot(eval)
#Cette fonction trace chacune des statistiques en fonctions des autres
cov(eval)
cor(eval)
#La corr�lation entre les r�sultats partiels et le r�sultat total est de 0.99
#Il y a donc corr�lation
#Les salaires des professeurs d�pendent directement des d�penses publiques (corr�lation de 0.86)
#Il n'y a aucunn rapport entre le rapport �l�ves/professeurs
#et les d�penses publiques
#Les d�penses publiques n'ont aucun impact sur la r�ussite des �l�ves
#Le param�tre qui a le plus grand impact sur la r�ussite des �l�ves est la participation des �l�ves

#Exercice 10
mention <- rep('sans',50)
mention[eval$total>=900]='AB'
mention[eval$total>=1000]='B'
mention[eval$total>=1100]='TB'
mention <- as.factor(mention)
eval$mention <- mention
boxplot(eval$depense~eval$mention)
#Les �tats o� il y a les meilleurs r�sultats sont ceux qui ont le moins d�pens�s dans l'enseignement
#Probablement � cause de l'enseignement priv�
plot(eval$particip[eval$mention=='TB'],eval$depense[eval$mention=='TB'],col='green',ylim=c(0,10),xlim = c(0,100))
points(eval$particip[eval$mention=='AB'],eval$depense[eval$mention=='AB'],col='orange')
points(eval$particip[eval$mention=='B'],eval$depense[eval$mention=='B'],col='yellow')
points(eval$particip[eval$mention=='sans'],eval$depense[eval$mention=='sans'],col='red')
#Les �tats o� il y a les meilleurs r�sultats sont ceux qui ont le moins d�pens�s dans l'enseignement
#Probablement � cause de l'enseignement priv�

#Exercice 11
class(mention)
nomb=table(eval$mention)
par(mfrow=c(1,2))
barplot(nomb)
plot(nomb)
#Le premier diagramme est un diagramme en barre, c'est donc l'aire de la barre qui repr�sente l'effectif
#Le second est un diagramme b�ton, seule la hauteur compte