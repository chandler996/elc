import { View, Text, Button } from '@tarojs/components'
import './index.css'
import { useEffect, useState } from 'react';
import request from '../../api/request' // 引入封装好的 axios 实例

// 定义类型
interface Product {
  id: string;
  name: string;
  price: number;
  stock: number;
}

export default function ProductList() {
  const [products, setProducts] = useState<Product[]>([]);
  const [selectedProduct, setSelectedProduct] = useState<string | null>(null);
  const [quantity, setQuantity] = useState<number>(1);
  const [orderResult, setOrderResult] = useState<{ orderId: string; totalPrice: number } | null>(null);
  const [loading, setLoading] = useState<boolean>(true)
  const [error, setError] = useState<string | null>(null)

  // 请求后端数据
  useEffect(() => {
    request
      .get<Product[]>('/products')
      .then(resp => {
        console.log(resp)
        setProducts(resp.data)
        setLoading(false)
      })
      .catch(error => {
        console.error('获取产品数据失败:', error)
        setError('无法加载产品数据，请检查网络或服务是否正常。')
        setLoading(false)
      })
  }, [])

  const placeOrder = async () => {
        if (!selectedProduct) return;
        try {
            const res = await request.post('/orders', {
                productId: selectedProduct,
                quantity
            });
            setOrderResult(res.data);
        } catch (e) {
            console.error(e);
        }
    };

  return (
    <View className='index'>
      <Text>产品信息</Text>
      {loading && <Text>正在加载数据...</Text>}
      {error && <Text style={{ color: 'red' }}>{error}</Text>}
      {!loading && !error && products.length === 0 && <Text>暂无产品数据</Text>}
      {!loading &&
        !error &&
        products.map(p => (
          <View key={p.id}>
            <Text>{p.name} - ${p.price} (库存: {p.stock})</Text>
            <Button onClick={() => setSelectedProduct(p.id)}>选择</Button>
          </View>
        ))}
      {selectedProduct && selectedProduct.length > 0 && (
        <View>
          <Text>数量：</Text>
          <input type="number" value={quantity} onChange={(e) => setQuantity(Number(e.target.value))} />
          <Button onClick={placeOrder}>提交订单</Button>
        </View>
      )}

      {orderResult && (
        <View>
          <Text>订单提交成功！</Text>
          <Text>订单ID: {orderResult.orderId}</Text>
          <Text>总价: ￥{orderResult.totalPrice.toFixed(2)}</Text>
        </View>
      )}
    </View>
  )
}
