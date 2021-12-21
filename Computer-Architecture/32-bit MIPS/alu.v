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
        if (srca < srcb) begin
          aluresult = 1;
        end else begin
          aluresult = 0;
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
