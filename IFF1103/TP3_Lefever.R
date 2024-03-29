#Exercice 1
runif(5)
rnorm(5)
rnorm(5,10,1)
data.exp <- rexp(300,4)
data.pois <- rpois(300,2)
dnorm(0)
pnorm(0,0,5)
x <- seq(0,4,length=100)
hist(data.exp,freq=FALSE)
lines(x,dexp(x,4))
#Bonne mod�lisation R
plot(table(data.pois)/300)
lines(seq(0,11,by=0.1),dpois(seq(0,11,by=0.1),2))
#Bonne mod�lisation R

#Exercice 2
tclbernoulli<-function(N,p){
  res= c()
  for (i in seq(1,1000,by=1)){
    S<-((sum(rbinom(N,1,p))/N)-p)*sqrt(N)/(sqrt(p*(1-p)))
    res = c(res,S)
  }
  return (res)
}
Exp<-tclbernoulli(1000,0.2)
count<-0
for (i in Exp){
  if (i>=-1.96 && i<=1.96){
    count<- count+1
  }
}
count
#On observe que le pourcentage correspond � peu pr�s � 95%
plot.ecdf(Exp)
lines(seq(-4,4,by=0.01),pnorm(seq(-4,4,by=0.01)))
#Th�or�me de la limite centrale?

#Exercice 3
Nfin <- 5000
X <- rexp(Nfin,2)
Y <- cumsum(X)/1:Nfin
plot(1:Nfin,Y,type = "l",ylim = c(0,1),xlab="n",ylab="moyenne empirique")
for (i in 2:50){
  X <- rexp(Nfin,2)
  Y <- cumsum(X)/1:Nfin
  lines(1:Nfin,Y,col = i)
}
#Ce programme trace les moyennes de 1 � 5000
#r�sultats d'exp�riences pouvant �tre mod�lis�s par 
#une loi exponentielle de param�tre variant de 2 � 50
#Ainsi on constate que plus le nombre d'exp�riences augmente
#et plus on tend vers l'esp�rance de la variable al�atoire
#On illustre donc la loi des grands nombres

lgnexpo <- function(N){
  moy=rep(0,100)
  for (i in 1:100){
    moy[i]=mean(rexp(N,2))
  }
  return(moy)}
#Renvoie un tableau de 100 moyennes de N r�sultats associ�s
#� une variable al�atoire de param�tre 2

par(mfrow=c(1,3))
boxplot(lgnexpo(100))
boxplot(lgnexpo(1000))
boxplot(lgnexpo(10000))
#Plus la valeur de N est �lev�e et plus la variance empirique est faible
#Ce qui revient � dire que plus on s'�loigne de l'esp�rance
#Moins on a de chance d'obtenir ce r�sultat
#Ce qui se rapporte au th�or�me de Bienaym�-Tchebychev

lgncauchy<- function(N){
  moy=rep(0,100)
  for (i in 1:100){
    moy[i]=mean(rcauchy(N,0,2))
  }
  return(moy)}

par(mfrow=c(1,3))
boxplot(lgncauchy(100),outline=FALSE)
boxplot(lgncauchy(1000),outline=FALSE)
boxplot(lgncauchy(10000),outline=FALSE)
#Outline permet de retirer les valeurs abh�rantes de la bo�te
#Ainsi on remarque, que lorsque outline est true, on a plus de valeurs �loign�es
#de la moyenne pour un grand nombre d'exp�riences
#On remarque enfin que lorsque outline est false, toutes les exp�riences
#ont globalement les m�mes valeurs remarquables

#Exercice 4
ech1=rbinom(1000,10,8/10)
ech2=rbinom(1000,20,8/20)
ech3=rbinom(1000,30,8/30)
ech4=rbinom(1000,100,8/100)

