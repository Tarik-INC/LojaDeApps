Especificações do projeto orientado a objetos

Objetivando a escrita de um programa consistente e coeso, bem como facilitando sua escrita, leitura e manutenção, esse documento contém normas e sugestões de desenvolvimento em Java, apoiando a programação em grupo e a estruturação do relatório.

Preferência do fecha e abre chaves:

Preferencial -> public Construtor() {  
	/////
	/////
}

Evitar -> public Construtor() 
{ 
    //////
/}

Comentários: Devem ser deixados uma boa quantidade de comentários no código. Se o método de correção for mantido como no semestre passado, a dupla irá avaliar bem um código comentado corretamente, além dos comentários ajudar na elaboração do relatório e na leitura do seu código por outras pessoas do grupo. A sugestão é deixar comentários sobre o porquê determinado atributo ou método foi escolhido como private ou public ou protected ou static, salvo casos já subentendidos como construtores public e mensagens, como funciona resumidamente determinado método, bem como a representabilidade da classe no programa e possíveis problemas que outras pessoas do grupo podem auxiliar a resolução. Por exemplo:

/* A classe estoque é responsável por representar a quantidade de produtos da loja bem como possuirá meios de soma e baixa de estoque */

class Estoque {

//////// ////////

}

Organização do projeto: Para cada arquivo .java deverá haver apenas uma classe, o nome do arquivo deve coincidir com o nome da classe; exemplo arquivo Main deve conter a classe Main. Cada arquivo logicamente relacionado deverá ser guardado em um pacote com um nome coerente. O conjunto de pacotes deve estar na pasta do projeto com nome livre. Será enviado a pasta do projeto src.

Sugestões:

IDE: Recomendo usar as seguintes IDE`S para facilitar na organização do espaço de trabalho e na escrita do código. InteliJ: https://www.jetbrains.com/help/idea/installing-and-launching.html

NetBeans: https://netbeans.org/community/releases/80/install.html

Eclipse:http://ubuntuhandbook.org/index.php/2016/01/how-to-install-the-latest-eclipse-in-ubuntu-16-04-15-10/

Pegar dados do Usuário em java: Algo nada trivial em java, existem 2 métodos principais, o primeiro é descrito no livro da Deitel, na qual se utiliza uma variável Scanner, já o segundo, é meu preferido, é através de um BufferReader, que retorna uma String
a maioria dos sites ensinam a fazer isso em 3 linhas, dei um jeito e consegui fazer em um)

para utilizar os dados é só acessar método readLine() da variável: BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); String Linha = br.readLine();

O motivo de usar bufferedReader ao invés do scanner é pq ele é mais rápido, mesmo se vc tiver que fazer cast.

3.Tratamento de Exceção(Ganhar pontos): É muito importante saber a diferença entre Throw, Throws e quando usar cada um em conjunto com o try/catch em Java. Recomendo ler o livro da Deitel, Java ™ 10a edição.
