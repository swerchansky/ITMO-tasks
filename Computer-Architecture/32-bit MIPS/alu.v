module alu(srca, srcb, alucontrol, aluresult, zero);
  input [31:0] srca, srcb;
  input [2:0] alucontrol;
  output reg [31:0] aluresult;
  output reg zero; 
   
  always @(srca, srcb, alucontrol) begin
    case (alucontrol)
      3'b000: 
        aluresult = srca & srcb;
      3'b001: 
        aluresult = srca | srcb;
      3'b010: 
        aluresult = srca + srcb;
      3'b110: 
        aluresult = srca - srcb;
      3'b111: begin 
        if (srca[31] == srcb[31]) begin 
                if (srca < srcb) begin
                    aluresult = 32'b1;
                end else begin
                    aluresult = 32'b0;
                end
            end else begin
                if (srca[31] > srcb[31]) begin
                    aluresult = 32'b1;
                end else begin
                    aluresult = 32'b0;
                end    
            end
      end
    endcase
  end

  always @(aluresult) begin
    if (aluresult == 0) begin
      zero = 1;
    end else begin
      zero = 0;
    end
  end
  
endmodule